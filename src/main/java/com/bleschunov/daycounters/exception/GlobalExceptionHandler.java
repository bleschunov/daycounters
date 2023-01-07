package com.bleschunov.daycounters.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Bleschunov Dmitry
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotUniqueException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleNotUniqueEntityException(
            EntityNotUniqueException exception
    ) {
        return exception.getMessage();
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFoundEntityException(
            EntityNotFoundException exception
    ) {
        return exception.getMessage();
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleBusinessException(
            BusinessException exception
    ) {
        return exception.getMessage();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleUnknownException(
            Exception exception
    ) {
        return exception.getMessage();
    }
}
