package com.exadel.service;

import com.exadel.model.entity.Training;
import com.exadel.model.entity.User;

import java.util.Collection;
import java.util.Optional;

public interface TrainingService {

    Optional<Training> getTrainingById(long id);

    Collection<Training> getAllTrainings();

    Optional<Training> addTraining(Training training);

    void addTrainer(User trainer, long id);
}
