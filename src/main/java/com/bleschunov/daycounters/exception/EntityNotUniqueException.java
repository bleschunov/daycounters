package com.bleschunov.daycounters.exception;

/**
 * @author Bleschunov Dmitry
 */
public class EntityNotUniqueException extends BusinessException {
    public EntityNotUniqueException(String message) {
        super(message);
    }

    public EntityNotUniqueException(String message, Throwable cause) {
        super(message, cause);
    }
}
