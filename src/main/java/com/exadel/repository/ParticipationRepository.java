package com.exadel.repository;

import com.exadel.model.entity.training.Participation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface ParticipationRepository extends JpaRepository<Participation, Long> {
    List<Participation> findByTrainingId(long trainingId);

    Participation findByTrainingIdAndUserId(long trainingId, long userId);

    long countByTrainingIdAndEndDayNotNull(long trainingId);
}
