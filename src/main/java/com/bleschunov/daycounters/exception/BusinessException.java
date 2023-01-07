package com.bleschunov.daycounters.exception;

import lombok.Getter;

/**
 * @author Bleschunov Dmitry
 */
@Getter
public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
