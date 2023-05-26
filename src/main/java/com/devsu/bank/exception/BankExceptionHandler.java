package com.devsu.bank.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class BankExceptionHandler {
    @ExceptionHandler(BankException.class)
    protected ResponseEntity<BankError> handleApiException(BankException e) {
        Integer statusCode = e.getStatus();
        BankError apiError = new BankError(e.getMessage());
        return ResponseEntity.status(statusCode).body(apiError);
    }

}
