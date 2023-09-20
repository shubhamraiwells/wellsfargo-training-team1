package com.banking.teamone.exception;

import org.springframework.security.core.AuthenticationException;

public class BankAccountExceptions {


    public static class BankAccountNotFoundException extends AuthenticationException {
        public BankAccountNotFoundException(String msg) {
            super(msg);
        }
    }
}
