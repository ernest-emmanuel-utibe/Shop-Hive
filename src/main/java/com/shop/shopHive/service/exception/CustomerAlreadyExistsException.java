package com.shop.shopHive.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CustomerAlreadyExistsException extends RuntimeException {
    public CustomerAlreadyExistsException() {
        super();
    }
    public CustomerAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
    public CustomerAlreadyExistsException(String message) {
        super(message);
    }
    public CustomerAlreadyExistsException(Throwable cause) {
        super(cause);
    }
}
