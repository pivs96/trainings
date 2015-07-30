package com.exadel.exception;

public class TrainerNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private String userId;

    public TrainerNotFoundException(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }
}
