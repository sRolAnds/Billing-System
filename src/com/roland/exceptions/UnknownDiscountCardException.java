package com.roland.exceptions;

public class UnknownDiscountCardException extends RuntimeException {

    public UnknownDiscountCardException() {}

    public UnknownDiscountCardException(String message) {
        super(message);
    }

    public UnknownDiscountCardException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnknownDiscountCardException(Throwable cause) {
        super(cause);
    }

    public UnknownDiscountCardException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
