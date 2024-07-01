package com.insurance.shared.exception;

public class EntitySaveException extends RuntimeException {
    public EntitySaveException(String message) {
        super(message);
    }
}
