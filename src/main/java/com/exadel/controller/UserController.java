package com.exadel.controller;

import com.exadel.model.entity.Employee;
import com.exadel.model.entity.ExternalTrainer;
import com.exadel.model.entity.Training;
import com.exadel.model.entity.User;
import com.exadel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value="/newTrainer", method = RequestMethod.POST)
    public ExternalTrainer addExternalTrainer(@RequestBody ExternalTrainer trainer, @RequestParam String trainingId) {
        //List<Training> trainings = new ArrayList<>();
        //trainings.add(TrainingsController.getTrainingById(trainingId));
        //trainer.setMentoringTrainings(trainings);
        return trainer;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<User> showUsers() {
        return (List<User>)userService.getAllUsers();
    }
}