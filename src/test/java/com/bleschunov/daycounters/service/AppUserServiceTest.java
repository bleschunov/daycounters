package com.bleschunov.daycounters.service;

import com.bleschunov.daycounters.dto.AppUserDto;
import com.bleschunov.daycounters.exception.EntityNotUniqueException;
import com.bleschunov.daycounters.mapper.AppUserMapperImpl;
import com.bleschunov.daycounters.repository.AppUserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * @author Bleschunov Dmitry
 */
@ExtendWith(MockitoExtension.class)
public class AppUserServiceTest {
    @Spy
    private AppUserMapperImpl appUserMapper;

    @Mock
    private AppUserRepository appUserRepository;

    @InjectMocks
    private AppUserService appUserService;

    @Test
    public void shouldCreateAppUser() {
        AppUserDto appUserDto = AppUserDto.builder().telegramId(Mockito.anyLong()).build();
        Mockito.when(appUserRepository.existsByTelegramId(appUserDto.getTelegramId())).thenReturn(false);

        appUserService.createUser(appUserDto);

        Mockito.verify(appUserRepository).save(Mockito.any());
    }

    @Test
    public void shouldThrowEntityNotUniqueException() {
        AppUserDto authDto = AppUserDto.builder().telegramId(Mockito.anyLong()).build();
        Mockito.when(appUserRepository.existsByTelegramId(authDto.getTelegramId())).thenReturn(true);

        Assertions.assertThrows(EntityNotUniqueException.class, () -> appUserService.createUser(authDto));
    }
}
