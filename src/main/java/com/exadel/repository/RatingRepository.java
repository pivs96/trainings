package com.exadel.repository;

import com.exadel.model.entity.training.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Long> {

    List<Rating> findByTrainingId(long trainingId);
}
