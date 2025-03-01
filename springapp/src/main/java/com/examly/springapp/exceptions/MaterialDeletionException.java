package com.examly.springapp.exceptions;

public class MaterialDeletionException extends Exception {
    public MaterialDeletionException() {}

    public MaterialDeletionException(String msg) {
        super(msg);
    }
}