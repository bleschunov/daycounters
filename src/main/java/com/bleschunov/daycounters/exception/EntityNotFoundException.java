package com.bleschunov.daycounters.exception;

/**
 * @author Bleschunov Dmitry
 */
public class EntityNotFoundException extends BusinessException{
    public EntityNotFoundException(String message) {
        super(message);
    }

    public EntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
