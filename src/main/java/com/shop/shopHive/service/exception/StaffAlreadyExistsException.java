package com.shop.shopHive.service.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class StaffAlreadyExistsException extends RuntimeException {
    public StaffAlreadyExistsException() {
        super();
    }
    public StaffAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
    public StaffAlreadyExistsException(String message) {
        super(message);
    }
    public StaffAlreadyExistsException(Throwable cause) {
        super(cause);
    }
}
