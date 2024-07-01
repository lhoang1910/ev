package com.insurance.shared.exception;

public class UnknownEventTypeException extends RuntimeException {
    public UnknownEventTypeException() {
    }

    public UnknownEventTypeException(String eventType) {
        super("unknown event type: " + eventType);
    }
}
