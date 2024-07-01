package com.insurance.shared.exception;

public record InternalServerErrorResponse(int status, String message, String timestamp) {
}
