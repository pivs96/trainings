package com.exadel.service;

import com.exadel.controller.Participation;
import com.exadel.model.entity.training.Training;

import java.util.Collection;
import java.util.Optional;

public interface TrainingService {

    Training getTrainingById(String id);

    Training getTrainingById(long id);

    Collection<Training> getAllTrainings();

    Optional<Training> addTraining(Training training);

    void updateTraining(Training training);

    Participation checkParticipation(String userId, String trainingId);

    void cancelById(String id);

    double addRating(int rating, String trainingId);
}
