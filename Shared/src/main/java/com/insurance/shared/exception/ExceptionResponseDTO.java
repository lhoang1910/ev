package com.insurance.shared.exception;

import java.time.LocalDateTime;

public record ExceptionResponseDTO(int Status, String message, LocalDateTime timestamp) {
}
