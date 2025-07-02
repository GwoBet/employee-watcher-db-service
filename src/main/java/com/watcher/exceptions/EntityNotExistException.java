package com.watcher.exceptions;

public class EntityNotExistException extends RuntimeException {

    public EntityNotExistException(String message) {
        super(message);
    }
}
