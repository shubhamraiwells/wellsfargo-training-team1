package com.banking.teamone.exception;

import org.hibernate.PropertyAccessException;
import org.springframework.security.core.AuthenticationException;

import javax.transaction.TransactionalException;

public class BankAccountExceptions {


    public static class BankAccountNotFoundException extends AuthenticationException {
        public BankAccountNotFoundException() {
            super("No bank account found for corresponding username/account number");
        }
    }

    public static class InsufficientFundsException extends RuntimeException {
        public InsufficientFundsException() {
            super("Insufficient funds in bank account to perform corresponding transaction");
        }
    }

    public static class AccountBlockedOrInactiveException extends RuntimeException {
        public AccountBlockedOrInactiveException() {
            super("Account is blocked or inactive");
        }
    }

    public static class InvalidAmountException extends RuntimeException {
        public InvalidAmountException() {
            super("Amount can't be less than or equal to zero");
        }
    }

}
