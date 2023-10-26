package org.lab.exceptions;

public class AccountNotFoundException extends Exception{
    public AccountNotFoundException(String message) {
        super(message);
    }
    public AccountNotFoundException() {
        super("Account not found.");
    }
}
