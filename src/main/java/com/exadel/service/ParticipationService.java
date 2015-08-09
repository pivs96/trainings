package com.exadel.service;

import com.exadel.model.entity.training.Participation;

import java.util.List;

public interface ParticipationService {

    List<Participation> getAllParticipationsByTrainingId(long trainingId);

    void addParticipation(Participation participation);

    Participation getParticipationByTrainingAndUserId(long trainingId, long userId);

    void deleteParticipation(long id);

    long countCompletedParticipants(long trainingId);
}
