package com.bleschunov.daycounters.controller;

import com.bleschunov.daycounters.exception.EntityNotFoundException;
import com.bleschunov.daycounters.service.CounterService;
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
public class CounterControllerTest {
    @Mock
    private CounterService counterService;

    @InjectMocks
    private CounterController counterController;

    @Test
    public void shouldCreateCounter() {
        counterController.createCounter(Mockito.any());

        Mockito.verify(counterService).createCounter(Mockito.any());
    }

    @Test
    public void shouldThrowEntityNotFoundException() {
        Mockito.doThrow(EntityNotFoundException.class).when(counterService).createCounter(Mockito.any());

        Assertions.assertThrows(EntityNotFoundException.class, () -> counterController.createCounter(Mockito.any()));
        Mockito.verify(counterService).createCounter(Mockito.any());
    }
}
