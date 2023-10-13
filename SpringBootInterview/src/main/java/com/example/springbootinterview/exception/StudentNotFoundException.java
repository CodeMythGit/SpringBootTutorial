package com.example.springbootinterview.exception;

public class StudentNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public StudentNotFoundException(String s) {
        super(s);
    }
}
