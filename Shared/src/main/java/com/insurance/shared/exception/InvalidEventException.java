package com.insurance.shared.exception;

public class InvalidEventException extends RuntimeException{
    public InvalidEventException() {
    }

    public InvalidEventException(String message) {
        super("invalid event: " + message);
    }
}