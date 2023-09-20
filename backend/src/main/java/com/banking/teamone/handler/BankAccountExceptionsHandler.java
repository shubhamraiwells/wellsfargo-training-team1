package com.banking.teamone.handler;

import com.banking.teamone.exception.BankAccountExceptions;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@RestController
@ControllerAdvice
public class BankAccountExceptionsHandler {
    @ExceptionHandler(value = BankAccountExceptions.BankAccountNotFoundException.class)
    public ResponseEntity handleBankAccountNotFoundException(BankAccountExceptions.BankAccountNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), OK);
    }

    @ExceptionHandler(value = BankAccountExceptions.InsufficientFundsException.class)
    public ResponseEntity handleUnsufficientFundsException(BankAccountExceptions.InsufficientFundsException exception) {
        return new ResponseEntity<>(exception.getMessage(), OK);
    }

    @ExceptionHandler(value = BankAccountExceptions.AccountBlockedOrInactiveException.class)
    public ResponseEntity handleAccountBlockedorInactiveException(BankAccountExceptions.AccountBlockedOrInactiveException exception) {
        return new ResponseEntity<>(exception.getMessage(), OK);
    }
}
