package com.nhorushko.task.dbatomiccounter.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ErrorMessage> handleIllegalArgumentException(Exception e) {
        var message = new ErrorMessage(e.getMessage());
        return ResponseEntity.status(418).body(message);
    }

    private record ErrorMessage(String message){}
}

