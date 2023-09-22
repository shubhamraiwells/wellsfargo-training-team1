package com.banking.teamone.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class DatabaseIntegrityException extends DataIntegrityViolationException {
    public DatabaseIntegrityException(String msg) {
        super(msg);
    }
}
