package com.exadel.service.impl;

import com.exadel.model.entity.training.Participation;
import com.exadel.repository.ParticipationRepository;
import com.exadel.service.ParticipationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ParticipationServiceImpl implements ParticipationService {
    @Autowired
    private ParticipationRepository participationRepository;

    @Override
    public List<Participation> getAllParticipationsByTrainingId(long trainingId) {
        return participationRepository.findByTrainingId(trainingId);
    }

    @Override
    public void addParticipation(Participation participation) {
        participationRepository.save(participation);
    }

    @Override
    @Modifying
    public Participation getParticipationByTrainingAndUserId(long trainingId, long userId) {
        return participationRepository.findByTrainingIdAndUserId(trainingId, userId);
    }

    @Override
    public void deleteParticipation(long id) {
        participationRepository.delete(id);
    }

    @Override
    public long countCompletedParticipants(long trainingId) {
        return participationRepository.countByTrainingIdAndEndDayNotNull(trainingId);
    }
}
