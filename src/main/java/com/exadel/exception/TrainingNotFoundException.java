package com.exadel.exception;

public class TrainingNotFoundException extends RuntimeException {

    private final String trainingId;

    public TrainingNotFoundException(String trainingId) {
        this.trainingId = trainingId;
    }

    public String getTrainingId() {
        return trainingId;
    }
}
