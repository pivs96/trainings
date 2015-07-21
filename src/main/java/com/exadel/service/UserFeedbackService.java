package com.exadel.service;

import com.exadel.model.entity.UserFeedback;

import java.util.Collection;
import java.util.Optional;

public interface UserFeedbackService {
    Optional<UserFeedback> getFeedbackById(long id);

    Collection<UserFeedback> getFeedbacksByVisitor(long id);

    UserFeedback addFeedback(UserFeedback feedback);
}