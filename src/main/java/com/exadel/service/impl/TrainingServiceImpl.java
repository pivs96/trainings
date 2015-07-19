package com.exadel.service.impl;

import com.exadel.model.entity.Training;
import com.exadel.repository.TrainingRepository;
import com.exadel.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class TrainingServiceImpl implements TrainingService{
    private final TrainingRepository trainingRepository;

    @Autowired
    public TrainingServiceImpl(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    @Override
    public Optional<Training> getTrainingById(long id) {
        return Optional.ofNullable(trainingRepository.findOne(id));
    }

    @Override
    public Collection<Training> getAllTrainings() {
        return trainingRepository.findAll(new Sort("name"));
    }

    @Override
    public Optional<Training> addTraining(Training training) {
        return Optional.ofNullable(trainingRepository.save(training));
    }
}
