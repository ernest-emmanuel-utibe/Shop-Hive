package com.shop.shopHive.service.exception;

public class InvalidTokenException extends Throwable {
    public InvalidTokenException(String token_is_expired_or_invalid) {
        super(token_is_expired_or_invalid);
    }
}
