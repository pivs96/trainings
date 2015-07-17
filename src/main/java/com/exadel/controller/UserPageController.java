/*
package com.exadel.controller;

import com.exadel.model.constants.TrainingStatus;
import com.exadel.model.entity.Employee;
import com.exadel.model.entity.Training;
import com.exadel.model.entity.UserFeedback;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserPageController {
    private static Logger logger = Logger.getLogger(UserPageController.class.getName());

    @RequestMapping(method = RequestMethod.GET)
    public Employee showUser(@RequestParam String id) {
        return new Employee();
    }

    @RequestMapping(value = "/mentoringTrainings", method = RequestMethod.GET)
    public List<Training> showMentoringTrainings(@RequestParam String userId) {
        Employee employee = getUserById(userId);
        return employee.getMentoringTrainings();
    }

    @RequestMapping(value = "/visitingTrainings", method = RequestMethod.GET)
    public List<Training> showVisitingTrainings(@RequestParam String userId) {
        Employee employee = getUserById(userId);
        return employee.getVisitingTrainings();
    }

    @RequestMapping(value = "/feedbacks", method = RequestMethod.GET)
    public List<UserFeedback> getFeedbacks(@RequestParam String userId) {
        List<UserFeedback> feedbacks = new ArrayList<>();
        feedbacks.add(new UserFeedback());
        feedbacks.add(new UserFeedback());
        return feedbacks;
    }

    @RequestMapping(value="/newFeedback", method = RequestMethod.POST)
    public UserFeedback createFeedback(@RequestParam String userId, @RequestBody UserFeedback feedback) {
        //add to db feedback
        return feedback;
    }
}

*/
