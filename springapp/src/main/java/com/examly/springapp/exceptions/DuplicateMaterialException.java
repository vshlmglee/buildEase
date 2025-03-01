package com.examly.springapp.exceptions;

public class DuplicateMaterialException extends Exception {
    public DuplicateMaterialException() {}

    public DuplicateMaterialException(String msg) {
        super(msg);
    }
}