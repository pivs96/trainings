package com.exadel.exception;

public class UserHasNotMentoringTrainingsException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private String userId;

    public UserHasNotMentoringTrainingsException(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }
}
