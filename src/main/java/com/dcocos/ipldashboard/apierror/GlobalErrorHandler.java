package com.dcocos.ipldashboard.apierror;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.dcocos.ipldashboard.exceptions.TeamNotFoundException;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

@Slf4j
@RestControllerAdvice
public class GlobalErrorHandler {

    @ExceptionHandler(TeamNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleException(TeamNotFoundException exception) {
        val apiErrorResponse = new ApiErrorResponse(NOT_FOUND, exception.getMessage());
        return logAndBuildResponse(apiErrorResponse, exception);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleException(Exception exception) {
        val apiErrorResponse = new ApiErrorResponse(INTERNAL_SERVER_ERROR, "Error processing request");
        return logAndBuildResponse(apiErrorResponse, exception);
    }

    private ResponseEntity<ApiErrorResponse> logAndBuildResponse(ApiErrorResponse errorResponse, Exception e) {
        if (errorResponse.getStatus().is5xxServerError()) {
            log.error("Exception handler mapped exception", e);
        } else {
            log.warn("Exception handler mapped exception", e);
        }
        return ResponseEntity
                .status(errorResponse.getStatus())
                .body(errorResponse);
    }
}
