package com.dcocos.ipldashboard.apierror;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class ApiErrorResponse {
    private final LocalDateTime timestamp;
    private final HttpStatus status;
    private final String message;

    public ApiErrorResponse(HttpStatus status, String message) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
