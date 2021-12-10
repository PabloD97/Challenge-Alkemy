package com.challenge.backend.exception;

public class PersonageException extends Exception{

    private static final long serialVersionUID = 1L;


    public PersonageException(String errorMessage) {
        super(errorMessage);
    }
}
