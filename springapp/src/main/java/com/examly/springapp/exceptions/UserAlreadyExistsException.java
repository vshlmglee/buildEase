package com.examly.springapp.exceptions;

public class UserAlreadyExistsException extends Exception {
    public UserAlreadyExistsException() {}

    public UserAlreadyExistsException(String msg) {
        super(msg);
    }
}