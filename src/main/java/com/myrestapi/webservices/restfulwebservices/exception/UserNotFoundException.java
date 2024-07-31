package com.myrestapi.webservices.restfulwebservices.exception;


public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String s) {
        super("No user present with id : "+s);
    }
}
