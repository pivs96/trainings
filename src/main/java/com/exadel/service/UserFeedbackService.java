package com.exadel.service;

import com.exadel.model.entity.feedback.UserFeedback;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface UserFeedbackService {
    Optional<UserFeedback> getFeedbackById(long id);

    List<UserFeedback> getFeedbacksByVisitor(long id);

    UserFeedback addFeedback(UserFeedback feedback);
}