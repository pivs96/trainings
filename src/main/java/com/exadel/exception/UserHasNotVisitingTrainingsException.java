package com.exadel.exception;

public class UserHasNotVisitingTrainingsException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private String userId;

    public UserHasNotVisitingTrainingsException(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }
}
