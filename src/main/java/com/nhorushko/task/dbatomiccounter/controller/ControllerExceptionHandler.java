package com.nhorushko.task.dbatomiccounter.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ErrorMessage> handleIllegalArgumentException(Exception e) {
        return ResponseEntity.status(418).body(new ErrorMessage(e.getMessage()));
    }

    private record ErrorMessage(String message){}
}

