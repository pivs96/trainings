package com.exadel.controller;

import com.exadel.model.entity.Employee;
import com.exadel.model.entity.ExternalTrainer;
import com.exadel.model.entity.Training;
import com.exadel.model.entity.User;
import com.exadel.service.TrainingService;
import com.exadel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final TrainingService trainingService;

    @Autowired
    public UserController(UserService userService, TrainingService trainingService) {
        this.userService = userService;
        this.trainingService = trainingService;
    }

    @RequestMapping(value="/newTrainer", method = RequestMethod.POST)
    public ExternalTrainer addExternalTrainer(@RequestBody ExternalTrainer trainer,
                                              @RequestParam long trainingId) {
        trainingService.addTrainer(trainer, trainingId);
        return trainer;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Set<User> showUsers() {
        return (Set<User>)userService.getAllUsers();
    }
}