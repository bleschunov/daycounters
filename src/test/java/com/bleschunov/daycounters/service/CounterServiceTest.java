package com.bleschunov.daycounters.service;

import com.bleschunov.daycounters.dto.CounterDto;
import com.bleschunov.daycounters.exception.EntityNotFoundException;
import com.bleschunov.daycounters.mapper.CounterMapperImpl;
import com.bleschunov.daycounters.model.AppUser;
import com.bleschunov.daycounters.repository.AppUserRepository;
import com.bleschunov.daycounters.repository.CounterRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

/**
 * @author Bleschunov Dmitry
 */
@ExtendWith(MockitoExtension.class)
public class CounterServiceTest {
    @Spy
    private CounterMapperImpl counterMapper;

    @Mock
    private CounterRepository counterRepository;

    @Mock
    private AppUserRepository appUserRepository;

    @InjectMocks
    private CounterService counterService;

    @Test
    public void shouldCreateCounter() {
        CounterDto counterDto = CounterDto.builder().ownerTelegramId(Mockito.anyLong()).build();
        AppUser appUser = AppUser.builder().build();
        Mockito.when(appUserRepository.findByTelegramId(counterDto.getOwnerTelegramId()))
                .thenReturn(Optional.of(appUser));

        counterService.createCounter(counterDto);

        Mockito.verify(counterRepository).save(Mockito.any());
    }

    @Test
    public void shouldThrowEntityNotFoundException() {
        CounterDto counterDto = CounterDto.builder().ownerTelegramId(Mockito.anyLong()).build();
        Mockito.when(appUserRepository.findByTelegramId(counterDto.getOwnerTelegramId()))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(EntityNotFoundException.class, () -> counterService.createCounter(counterDto));
    }
}
