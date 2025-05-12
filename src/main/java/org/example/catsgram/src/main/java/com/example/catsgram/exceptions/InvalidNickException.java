package com.example.catsgram.exceptions;

public class InvalidNickException extends RuntimeException {
    public InvalidNickException(String message) {
        super(message);
    }
}
