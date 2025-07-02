package com.watcher.exceptions;

public class BaseDBSException extends RuntimeException {


    public BaseDBSException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public BaseDBSException(String message, String serviceExceptionName, Throwable throwable) {
        super(serviceExceptionName + ": " + message, throwable);
    }
}
