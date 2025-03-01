package com.examly.springapp.exceptions;

public class ContentNotFoundException extends Exception{
    public ContentNotFoundException() {}

    public ContentNotFoundException(String msg) {
        super(msg);
    }
}
