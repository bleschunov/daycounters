package com.bleschunov.daycounters.controller;

import com.bleschunov.daycounters.exception.EntityNotUniqueException;
import com.bleschunov.daycounters.service.AppUserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * @author Bleschunov Dmitry
 */
@ExtendWith(MockitoExtension.class)
public class AppUserControllerTest {
    @Mock
    private AppUserService appUserService;

    @InjectMocks
    private AppUserController appUserController;

    @Test
    public void shouldCreateAppUser() {
        appUserController.createUser(Mockito.any());

        Mockito.verify(appUserService).createUser(Mockito.any());
    }

    @Test
    public void shouldThrowEntityNotUniqueException() {
        appUserController.createUser(Mockito.any());
        Mockito.doThrow(EntityNotUniqueException.class).when(appUserService).createUser(Mockito.any());

        Assertions.assertThrows(EntityNotUniqueException.class, () -> appUserController.createUser(Mockito.any()));
    }
}
