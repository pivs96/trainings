package com.exadel.exception;

public class UserNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private String userId;

    public UserNotFoundException(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }
}
