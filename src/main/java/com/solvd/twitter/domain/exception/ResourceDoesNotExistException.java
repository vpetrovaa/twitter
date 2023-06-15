package com.solvd.twitter.domain.exception;

public class ResourceDoesNotExistException extends RuntimeException {

    public ResourceDoesNotExistException(final String message) {
        super(message);
    }

}
