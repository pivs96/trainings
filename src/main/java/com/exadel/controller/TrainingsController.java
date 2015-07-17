package com.exadel.controller;

import com.exadel.dto.TrainingDTO;
import com.exadel.model.entity.training.Training;
import com.exadel.model.entity.user.ExternalTrainer;
import com.exadel.service.TrainingService;
import com.exadel.service.UserService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;


import java.util.ArrayList;
import java.util.List;

@RestController
@PreAuthorize("hasAnyRole('0','1','2')")
@RequestMapping(value = "/trainings", headers = "Accept=application/json")

public class TrainingsController {
    @Autowired
    private TrainingService trainingService;

    @Autowired
    private UserService userService;

    @PreAuthorize("hasAnyRole('0','1','2')")
    @RequestMapping(method = RequestMethod.GET)
    public List<TrainingDTO> getTrainings() {
        List<Training> trainings = (List<Training>) trainingService.getAllTrainings();
        List<TrainingDTO> trainingDTOs = new ArrayList<>();
        for (Training training : trainings) {
            trainingDTOs.add(new TrainingDTO(training));
        }
        return trainingDTOs;
    }

    @PreAuthorize("hasAnyRole('0','1','2')")
    @RequestMapping(value = "/newTraining", method = RequestMethod.POST)
    public void createTraining(@RequestBody TrainingDTO trainingDTO) {
        Training training = new Training(trainingDTO);
        ExternalTrainer trainer = userService.getTrainerById(String.valueOf(trainingDTO.getTrainerId()));
        training.setTrainer(trainer);
        trainingService.addTraining(training);
    }
}