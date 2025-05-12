package com.example.catsgram.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleIncorrectParamException(final IncorrectParamException e) {
        return ErrorResponse.builder()
                .success(false)
                .error(e.getMessage() + " не корректный")
                .build();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleInvalidEmailException(final InvalidEmailException e) {
        return ErrorResponse.builder()
                .success(false)
                .error(e.getMessage() + " не корректная эл. почта")
                .build();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleInvalidNickException(final InvalidNickException e) {
        return ErrorResponse.builder()
                .success(false)
                .error(e.getMessage() + " не корректная ник")
                .build();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handlePostNotFoundException(final PostNotFoundException e){
        return ErrorResponse.builder()
                .success(false)
                .error(e.getMessage())
                .build();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleUserNotFoundException(final UserNotFoundException e){
        return ErrorResponse.builder()
                .success(false)
                .error(e.getMessage())
                .build();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleUserAlreadyExistException(final UserAlreadyExistException e){
        return ErrorResponse.builder()
                .success(false)
                .error(e.getMessage())
                .build();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleThrowable(final Throwable e) {
        return ErrorResponse.builder()
                .success(false)
                .error("Произошла ошибка: " + e.getMessage())
                .build();
    }
}
