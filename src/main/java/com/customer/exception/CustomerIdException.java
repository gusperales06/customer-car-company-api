package com.customer.exception;

public class CustomerIdException extends IllegalArgumentException {
    public CustomerIdException(final String message) {
        super(message);
    }
}
