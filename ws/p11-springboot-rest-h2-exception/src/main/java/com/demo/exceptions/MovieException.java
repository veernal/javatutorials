package com.demo.exceptions;

public class MovieException extends Exception {

    public MovieException() {
    }

    public MovieException(String message) {
        super(message);
    }

    public MovieException(Throwable cause) {
        super(cause);
    }

    public MovieException(String message, Throwable cause) {
        super(message, cause);
    }

    public MovieException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
