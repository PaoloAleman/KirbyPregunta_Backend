package com.pregunta.kirby.exception;

public class NonExistingUserException extends Exception {
    public NonExistingUserException(String message) {
        super(message);
    }
}
