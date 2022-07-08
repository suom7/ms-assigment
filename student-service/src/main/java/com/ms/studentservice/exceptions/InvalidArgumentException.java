package com.ms.studentservice.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidArgumentException extends RuntimeException {

    private final HttpStatus status;

    public InvalidArgumentException(String message) {
        super(message);
        this.status = HttpStatus.BAD_REQUEST;
    }

}
