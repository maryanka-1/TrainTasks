package com.example.catsgram.exceptions;

public class IncorrectParamException extends RuntimeException {
    public IncorrectParamException(String message) {
        super(message);
    }
}
