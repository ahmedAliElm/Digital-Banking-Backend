package org.example.digital_banking_backend.exceptions;

public class BalanceNotEnoughException extends Exception {
    public BalanceNotEnoughException(String message) {
        super(message);
    }
}

