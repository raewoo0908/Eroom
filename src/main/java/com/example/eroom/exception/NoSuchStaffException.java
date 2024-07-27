package com.example.eroom.exception;

public class NoSuchStaffException extends RuntimeException {
    public NoSuchStaffException() {
        super();
    }

    public NoSuchStaffException(String message) {
        super(message);
    }

    public NoSuchStaffException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchStaffException(Throwable cause) {
        super(cause);
    }

    protected NoSuchStaffException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
