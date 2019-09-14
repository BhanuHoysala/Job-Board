package com.heavenhr.hhrh.exceptions;

public class ValidationException extends Exception {

    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    public ValidationException(String message) {
        this.message = message;
    }

}
