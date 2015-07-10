package com.exadel.controller;

import com.exadel.model.entity.Training;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/trainings")
public class TrainingsController {
    List<Training> trainings = new ArrayList<Training>();

    /*private static TrainingsController ourInstance = new TrainingsController();

    public static TrainingsController getInstance() {
        return ourInstance;
    }

    private TrainingsController() {
    }*/

    public void setTrainings(){
        trainings.add(new Training("1", "Vasya", "204", new Date(), new Date(), new Date(), "1st best trainer", "developers", 4.5, 32));
        trainings.add(new Training("1", "Petya", "205", new Date(), new Date(), new Date(), "2nd best trainer", "developers", 2.7, 27));
        trainings.add(new Training("1", "Ivan", "206", new Date(), new Date(), new Date(), "3rd best trainer", "developers", 4.7, 102));
    }

    @RequestMapping(method= RequestMethod.GET)
    public @ResponseBody List<Training> getTrainings(){
        setTrainings();
        return trainings;
    }
}