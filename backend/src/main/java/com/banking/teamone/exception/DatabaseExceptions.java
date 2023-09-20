package com.banking.teamone.exception;

import org.springframework.dao.DataIntegrityViolationException;

public class DatabaseExceptions {
    public static class AadharCardRegisteredException extends DataIntegrityViolationException {
        public AadharCardRegisteredException(String msg) {
            super(msg);
        }
    }
}
