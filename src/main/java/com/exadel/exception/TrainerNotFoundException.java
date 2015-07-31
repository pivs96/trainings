package com.exadel.exception;

public class TrainerNotFoundException extends RuntimeException {

    private String userId;

    public TrainerNotFoundException(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }
}
