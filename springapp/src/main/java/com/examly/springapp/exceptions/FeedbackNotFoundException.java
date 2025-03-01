package com.examly.springapp.exceptions;

public class FeedbackNotFoundException extends Exception {
    public FeedbackNotFoundException() {}

    public FeedbackNotFoundException(String msg) {
        super(msg);
    }
}