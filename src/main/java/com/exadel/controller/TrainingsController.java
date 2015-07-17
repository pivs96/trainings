package com.exadel.controller;

import com.exadel.model.constants.TrainingStatus;
import com.exadel.model.entity.Employee;
import com.exadel.model.entity.Training;
import com.exadel.model.entity.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/trainings")
public class TrainingsController {
    private static Logger logger = Logger.getLogger(TrainingsController.class.getName());

   /* @RequestMapping(method=RequestMethod.GET)
    public List<Training> getTrainings(){
        return trainings;
    }

    @RequestMapping(value="/newTraining", method = RequestMethod.POST)
    public Training createTraining(@RequestBody Training training) {
        trainings.add(training);
        return training;
    }
    @RequestMapping(value="/training", method = RequestMethod.DELETE)
    public void deleteTraining(@RequestParam String id) {
        Training trainingToDelete = TrainingsController.getTrainingById(id);
        if (trainingToDelete != null) {
            trainings.remove(trainingToDelete);
        }
    }*/
}