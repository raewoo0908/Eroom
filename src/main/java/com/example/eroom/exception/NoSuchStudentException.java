package com.example.eroom.exception;

public class NoSuchStudentException extends RuntimeException{
    public NoSuchStudentException() {
        super();
    }

    public NoSuchStudentException(String message) {
        super(message);
    }

    public NoSuchStudentException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchStudentException(Throwable cause) {
        super(cause);
    }

    protected NoSuchStudentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
