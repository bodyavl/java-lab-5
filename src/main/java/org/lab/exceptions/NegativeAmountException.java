package org.lab.exceptions;

public class NegativeAmountException extends Exception{
    public NegativeAmountException(String message) {
        super(message);
    }
    public NegativeAmountException() {
        super("Amount must be positive");
    }
}
