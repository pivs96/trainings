package com.exadel.service;

import com.exadel.model.entity.training.Participation;

import java.util.List;

public interface ParticipationService {

    List<Participation> getAllParticipationsByTrainingId(long trainingId);

    void addParticipation(Participation participation);
}
