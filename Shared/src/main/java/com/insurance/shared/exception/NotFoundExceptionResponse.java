package com.insurance.shared.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotFoundExceptionResponse {
    private String message;
    private String timestamp;
    private int status;
}