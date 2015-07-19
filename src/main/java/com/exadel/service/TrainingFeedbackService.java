package com.exadel.service;

import com.exadel.model.entity.TrainingFeedback;

import java.util.Collection;
import java.util.Optional;

public interface TrainingFeedbackService {

    Optional<TrainingFeedback> getTrainingFeedbackById(long id);

    Collection<TrainingFeedback> getAllTrainingFeedbacks();

    Optional<TrainingFeedback> addTrainingFeedback(TrainingFeedback trainingFeedback);
}
