package com.exadel.exception;

public class TrainingNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private final String trainingId;

    public TrainingNotFoundException(String trainingId) {
        this.trainingId = trainingId;
    }

    public String getTrainingId() {
        return trainingId;
    }
}
