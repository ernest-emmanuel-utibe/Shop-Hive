package com.shop.shopHive.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class MealNotFoundException extends RuntimeException {

    public MealNotFoundException() {
        super();
    }
    public MealNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    public MealNotFoundException(String message) {
        super(message);
    }
    public MealNotFoundException(Throwable cause) {
        super(cause);
    }
}
