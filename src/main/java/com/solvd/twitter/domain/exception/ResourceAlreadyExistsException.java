package com.solvd.twitter.domain.exception;

public class ResourceAlreadyExistsException extends RuntimeException {

    public ResourceAlreadyExistsException(final String message) {
        super(message);
    }

}
