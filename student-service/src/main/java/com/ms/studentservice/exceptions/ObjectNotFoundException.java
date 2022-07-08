package com.ms.studentservice.exceptions;

import org.springframework.http.HttpStatus;

public class ObjectNotFoundException extends RuntimeException {

    private final HttpStatus status;
    public ObjectNotFoundException(String message) {
        super(message);
        this.status = HttpStatus.NOT_FOUND;
    }
}
