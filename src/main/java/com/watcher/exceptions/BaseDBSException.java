package com.watcher.exceptions;

public class BaseDBSException extends RuntimeException {

    public BaseDBSException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
