package com.examly.springapp.exceptions;

public class MaterialRequestException extends Exception {
    public MaterialRequestException() {}

    public MaterialRequestException(String msg) {
        super(msg);
    }
}