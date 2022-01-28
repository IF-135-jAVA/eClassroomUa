package com.softserve.betterlearningroom.exception;

public class SubmissionNotAllowedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public SubmissionNotAllowedException(String message) {
        super(message);
    }
}
