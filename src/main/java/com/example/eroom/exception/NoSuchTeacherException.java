package com.example.eroom.exception;

public class NoSuchTeacherException extends RuntimeException {

    public NoSuchTeacherException() {
        super();
    }

    public NoSuchTeacherException(String message) {
        super(message);
    }

    public NoSuchTeacherException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchTeacherException(Throwable cause) {
        super(cause);
    }

    protected NoSuchTeacherException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
