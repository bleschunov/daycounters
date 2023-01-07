package com.bleschunov.daycounters.rest.service;

import com.bleschunov.daycounters.rest.dto.CounterDto;
import com.bleschunov.daycounters.rest.exception.EntityNotFoundException;
import com.bleschunov.daycounters.rest.mapper.CounterMapper;
import com.bleschunov.daycounters.rest.model.Counter;
import com.bleschunov.daycounters.rest.repository.AppUserRepository;
import com.bleschunov.daycounters.rest.repository.CounterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

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

    public long getCounterValue(String title) {
        Counter counter = counterRepository.findByTitle(title).orElseThrow(() -> {
            throw new EntityNotFoundException(String.format(
                "Counter with title = %s does not exist.",
                title
            ));
        });

        return Duration.between(counter.getCreatedAt(), LocalDateTime.now()).toSeconds();
    }
}
