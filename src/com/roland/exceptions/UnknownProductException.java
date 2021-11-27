package com.roland.exceptions;

public class UnknownProductException extends RuntimeException {

    public UnknownProductException() {}

    public UnknownProductException(String message) {
        super(message);
    }

    public UnknownProductException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnknownProductException(Throwable cause) {
        super(cause);
    }

    public UnknownProductException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
