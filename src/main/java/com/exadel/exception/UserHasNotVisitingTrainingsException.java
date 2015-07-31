package com.exadel.exception;

public class UserHasNotVisitingTrainingsException extends RuntimeException {

    private String userId;

    public UserHasNotVisitingTrainingsException(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }
}
