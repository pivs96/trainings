package com.exadel.exception;

public class UserHasNotMentoringTrainingsException extends RuntimeException {

    private String userId;

    public UserHasNotMentoringTrainingsException(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }
}