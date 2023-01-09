package com.bleschunov.daycounters.telegrambot.exception;

/**
 * @author Bleschunov Dmitry
 */
public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
