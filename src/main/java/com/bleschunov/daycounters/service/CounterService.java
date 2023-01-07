package com.bleschunov.daycounters.service;

import com.bleschunov.daycounters.dto.CounterDto;
import com.bleschunov.daycounters.exception.EntityNotFoundException;
import com.bleschunov.daycounters.mapper.CounterMapper;
import com.bleschunov.daycounters.model.Counter;
import com.bleschunov.daycounters.repository.AppUserRepository;
import com.bleschunov.daycounters.repository.CounterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Bleschunov Dmitry
 */
@Service
@RequiredArgsConstructor
public class CounterService {
    private final CounterMapper counterMapper;

    private final CounterRepository counterRepository;
    private final AppUserRepository appUserRepository;

    public void createCounter(CounterDto counterDto) {
        appUserRepository.findByTelegramId(counterDto.getOwnerTelegramId()).ifPresentOrElse(appUser -> {
                    Counter transientCounter = counterMapper.toEntity(counterDto);
                    transientCounter.setOwner(appUser);
                    counterRepository.save(transientCounter);
                },
                () -> {
                    throw new EntityNotFoundException(String.format(
                            "User with telegramId = %s does not exist.",
                            counterDto.getOwnerTelegramId())
                    );
                }
            );
    }
}
