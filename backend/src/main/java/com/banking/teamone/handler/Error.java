package com.banking.teamone.handler;

import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

public class Error {
    private final int status;
    private final List<String> message;
    Error(int status, List<String> message) {
        this.status = status;
        this.message = message;
    }
    public int getStatus() {
        return status;
    }
    public List<String> getMessage() {
        return message;
    }
    public static Error processFieldErrors(List<org.springframework.validation.FieldError> fieldErrors) {
        List<String> errorList = new ArrayList<String>();
        for (FieldError fieldError: fieldErrors) {
            String errorString = String.format("%s %s", fieldError.getField(), fieldError.getDefaultMessage());
            errorList.add(errorString);
        }
        return new Error(BAD_REQUEST.value(), errorList);
    }
}
