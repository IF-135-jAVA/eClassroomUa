package com.softserve.betterlearningroom.exception;

public class UserAlreadyConfirmedException extends Exception {
    
    private static final long serialVersionUID = 1L;

    public UserAlreadyConfirmedException(String message) {
        super(message);
    }

}
