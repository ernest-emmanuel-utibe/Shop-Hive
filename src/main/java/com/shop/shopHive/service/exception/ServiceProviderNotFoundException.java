package com.shop.shopHive.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ServiceProviderNotFoundException extends RuntimeException{

    public ServiceProviderNotFoundException() {
        super();
    }
    public ServiceProviderNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    public ServiceProviderNotFoundException(String message) {
        super(message);
    }
    public ServiceProviderNotFoundException(Throwable cause) {
        super(cause);
    }
}
