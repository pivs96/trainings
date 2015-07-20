package com.exadel.service;

import com.exadel.model.entity.Rating;

import java.util.Collection;
import java.util.Optional;

public interface RatingService {

    Optional<Rating> getRatingById(long id);

    Collection<Rating> getAllRatingsByTrainingId(long trainingId);

    Collection<Rating> getAllRatings();

    void addRating(Rating rating);
}
