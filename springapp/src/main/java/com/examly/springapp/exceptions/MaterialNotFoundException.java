package com.examly.springapp.exceptions;

public class MaterialNotFoundException extends Exception {
    public MaterialNotFoundException() {}

    public MaterialNotFoundException(String msg) {
        super(msg);
    }
}