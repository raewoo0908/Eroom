package com.example.eroom.exception;

public class NoSuchAdminWorkException extends RuntimeException {
    public NoSuchAdminWorkException() {
        super();
    }

    public NoSuchAdminWorkException(String message) {
        super(message);
    }

    public NoSuchAdminWorkException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchAdminWorkException(Throwable cause) {
        super(cause);
    }

    protected NoSuchAdminWorkException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
