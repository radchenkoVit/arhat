package com.radchenko.arhat.exceptions;

public class UserNotRegisteredException extends RuntimeException {
    public UserNotRegisteredException() {
    }

    public UserNotRegisteredException(String message) {
        super(message);
    }

    public UserNotRegisteredException(String message, Throwable cause) {
        super(message, cause);
    }
}
