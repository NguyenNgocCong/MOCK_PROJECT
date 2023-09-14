package com.mock_project.mock_project.exception;

public class BadCredentialsLoginException extends RuntimeException {
    public BadCredentialsLoginException(String message) {
        super(message);
    }
}
