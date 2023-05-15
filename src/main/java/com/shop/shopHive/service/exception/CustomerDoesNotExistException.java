package com.shop.shopHive.service.exception;

public class CustomerDoesNotExistException extends RuntimeException {

    public CustomerDoesNotExistException(String message) {
        super(message);
    }

    public CustomerDoesNotExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
