package com.bleschunov.daycounters.rest.service;

import com.bleschunov.daycounters.rest.dto.CounterDto;
import com.bleschunov.daycounters.rest.exception.EntityNotFoundException;
import com.bleschunov.daycounters.rest.mapper.CounterMapperImpl;
import com.bleschunov.daycounters.rest.model.AppUser;
import com.bleschunov.daycounters.rest.repository.AppUserRepository;
import com.bleschunov.daycounters.rest.repository.CounterRepository;
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
