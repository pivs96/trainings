package com.exadel.repository;

import com.exadel.model.entity.training.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface TrainingRepository extends JpaRepository<Training, Long> {

}
