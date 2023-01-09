package com.bleschunov.daycounters.rest.service;

import com.bleschunov.daycounters.rest.dto.CounterDto;
import com.bleschunov.daycounters.rest.exception.BusinessException;
import com.bleschunov.daycounters.rest.exception.EntityNotFoundException;
import com.bleschunov.daycounters.rest.mapper.CounterMapper;
import com.bleschunov.daycounters.rest.model.Counter;
import com.bleschunov.daycounters.rest.repository.AppUserRepository;
import com.bleschunov.daycounters.rest.repository.CounterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Consumer;

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

    public List<Counter> getAllCountersByOwnerTelegramId(long telegramId) {
        return counterRepository.findAllByOwnerTelegramId(telegramId);
    }

    public long getCounterValue(long counterId, long ownerTelegramId) {
        Counter counter = counterRepository.findById(counterId).orElseThrow(() -> {
            throw new EntityNotFoundException(String.format(
                "Counter with title = %s does not exist.", counterId
            ));
        });

        assertRules(ownerTelegramId, counter);

        return getCounterValue(counter);
    }

    public void resetCounterById(long counterId, long ownerTelegramId) {
        doIfPresentAndHasRulesOrElseThrowException(counterId, ownerTelegramId, counter -> {
            long counterValue = getCounterValue(counter);
            if (counter.getMaxValue() < counterValue) {
                counter.setMaxValue(counterValue);
            }
            counter.setCountdownTime(LocalDateTime.now());
            counterRepository.save(counter);
        });
    }

    public void deleteCounterById(long counterId, long ownerTelegramId) {
        doIfPresentAndHasRulesOrElseThrowException(counterId, ownerTelegramId, counterRepository::delete);
    }

    private void doIfPresentAndHasRulesOrElseThrowException(
            long counterId,
            long ownerTelegramId,
            Consumer<Counter> action
    ) {
        counterRepository.findById(counterId).ifPresentOrElse(
                counter -> {
                    assertRules(ownerTelegramId, counter);
                    action.accept(counter);
                },
                () -> {
                    throw new EntityNotFoundException(String.format(
                            "Counter with title = %s does not exist.", counterId
                    ));
                }
        );
    }

    private void assertRules(long ownerTelegramId, Counter counter) {
        if (counter.getOwner().getTelegramId() != ownerTelegramId) {
            throw new BusinessException(String.format(
                "You don't have enough rules to use counter with id = %d.",
                counter.getId()
            ));
        }
    }

    private long getCounterValue(Counter counter) {
        return Duration.between(counter.getCountdownTime(), LocalDateTime.now()).toSeconds();
    }
}
