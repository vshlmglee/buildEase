package com.examly.springapp.exceptions;

public class InvalidFeedbackException extends Exception {
    public InvalidFeedbackException() {}

    public InvalidFeedbackException(String msg) {
        super(msg);
    }
}