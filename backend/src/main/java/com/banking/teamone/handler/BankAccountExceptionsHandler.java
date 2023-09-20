package com.banking.teamone.handler;

import com.banking.teamone.exception.BankAccountExceptions;
import com.banking.teamone.exception.DatabaseExceptions;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestController
@ControllerAdvice
public class BankAccountExceptionsHandler {
    @ExceptionHandler(value = BankAccountExceptions.BankAccountNotFoundException.class)
    public ResponseEntity handleBankAccountNotFoundException(BankAccountExceptions.BankAccountNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), BAD_REQUEST);
    }
}
