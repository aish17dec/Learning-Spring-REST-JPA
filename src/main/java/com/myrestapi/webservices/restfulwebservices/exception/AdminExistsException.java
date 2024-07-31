package com.myrestapi.webservices.restfulwebservices.exception;

public class AdminExistsException extends RuntimeException{
    public AdminExistsException(String message) {
        super(message);
    }
}
