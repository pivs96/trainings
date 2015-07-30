package com.exadel.service;

import com.exadel.model.entity.training.Rating;

import java.util.Collection;
import java.util.Optional;

public interface RatingService {

    Optional<Rating> getRatingById(long id);

    Collection<Rating> getAllRatingsByTrainingId(long trainingId);

    Collection<Rating> getAllRatings();

    boolean addRating(Rating rating);
}
