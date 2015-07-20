package com.exadel.controller;

import com.exadel.model.constants.UserRole;
import com.exadel.model.entity.*;
import com.exadel.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserPageController {
    private static Logger logger = Logger.getLogger(UserPageController.class.getName());
    private final UserService userService;

    @Autowired
    public UserPageController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public User showUser(@RequestParam String id) {
        Optional<User> user = userService.getUserById(Long.parseLong(id));
        return user.get();
    }

    @RequestMapping(value = "/mentoringTrainings", method = RequestMethod.GET)
    public List<Training> showMentoringTrainings(@RequestParam String userId) {
        User user = userService.getUserById(Long.parseLong(userId)).get();
        if (user.getRole() == UserRole.ADMIN || user.getRole() == UserRole.EMPLOYEE)
            return ((Employee)user).getMentoringTrainings();
        else if (user.getRole() == UserRole.EXTERNAL_TRAINER)
            return ((ExternalTrainer)user).getMentoringTrainings();

        return null; // TODO: EXCEPTION HANDLING!!!
    }

    @RequestMapping(value = "/visitingTrainings", method = RequestMethod.GET)
    public List<Training> showVisitingTrainings(@RequestParam String userId) {
        Employee employee = (Employee) userService.getUserById(Long.parseLong(userId)).get();
        return employee.getVisitingTrainings();
    }

    @RequestMapping(value = "/feedbacks", method = RequestMethod.GET)
    public List<UserFeedback> getFeedbacks(@RequestParam String userId) {
        Employee employee = (Employee) userService.getUserById(Long.parseLong(userId)).get();
        List<UserFeedback> feedbacks = employee.getReceivedFeedbacks();
        return feedbacks;
    }

    @RequestMapping(value = "/newFeedback", method = RequestMethod.POST)
    public UserFeedback createFeedback(@RequestParam String userId, @RequestBody UserFeedback feedback) {
        //add to db feedback
        return feedback;
    }
}

