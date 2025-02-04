package com.project.app.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<GlobalErrorResponse> getGlobalException(Exception exception) {
        GlobalErrorResponse globalErrorResponse = new GlobalErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                exception.getMessage());

        return new ResponseEntity<>(globalErrorResponse, HttpStatus.BAD_REQUEST);
    }

}
