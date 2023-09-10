package com.mock_project.mock_project.exception;

public class UserNameExistedException extends RuntimeException{

    public UserNameExistedException(String message) {
        super(message);
    }
}
