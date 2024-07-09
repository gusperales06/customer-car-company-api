package com.customer.exception;

public class NonCustomerChangesException extends IllegalArgumentException {
    public NonCustomerChangesException(String message) {
        super(message);
    }
}
