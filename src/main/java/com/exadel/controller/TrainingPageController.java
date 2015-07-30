package com.exadel.controller;

import com.exadel.dto.RatingDTO;
import com.exadel.dto.TrainingDTO;
import com.exadel.dto.TrainingFeedbackDTO;
import com.exadel.dto.UserDTO;
import com.exadel.model.entity.training.Entry;
import com.exadel.model.entity.training.Rating;
import com.exadel.model.entity.training.Training;
import com.exadel.model.entity.feedback.TrainingFeedback;
import com.exadel.model.entity.user.User;
import com.exadel.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/training")
public class TrainingPageController {
    @Autowired
    private TrainingFeedbackService trainingFeedbackService;

    @Autowired
    private EntryService entryService;

    @Autowired
    private TrainingService trainingService;

    @Autowired
    private RatingService ratingService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/participants", method = RequestMethod.GET)
    public List<UserDTO> getParticipants(@RequestParam String id) {
        Training training = trainingService.getTrainingById(id);
        List<UserDTO> userDTOs = new ArrayList<>();
        for (User user : training.getParticipants()) {
            userDTOs.add(new UserDTO(user));
        }
        return userDTOs;
    }

    @RequestMapping(value = "/check_participation", method = RequestMethod.GET)
    public Participation checkParticipation(@RequestParam String userId, @RequestParam String trainingId) {
        return trainingService.checkParticipation(userId, trainingId);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Participation register(@RequestParam String userId, @RequestParam String trainingId) {
        Training training = trainingService.getTrainingById(trainingId);
        User visitor = new User();
        visitor.setId(Long.parseLong(userId));
        training.addParticipant(visitor);
        trainingService.addTraining(training);

        if (training.getMembersCount() > training.getMembersCountMax())
            return Participation.RESERVE;
        else
            return Participation.MEMBER;
    }

    @RequestMapping(value = "/rating", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public double addTrainingRating(@RequestBody RatingDTO ratingDTO) {
        String trainingId = String.valueOf(ratingDTO.getTrainingId());
        ratingService.addRating(new Rating(trainingService.getTrainingById(trainingId),
                userService.getUserById(String.valueOf(ratingDTO.getUserId()))));
        return trainingService.addRating(ratingDTO.getRating(), trainingId);
    }

    @RequestMapping(value = "/feedbacks", method = RequestMethod.GET)
    public List<TrainingFeedbackDTO> getFeedbacks(@RequestParam String id) {
        Training training = trainingService.getTrainingById(id);
        List<TrainingFeedback> feedbacks = (List<TrainingFeedback>)
                trainingFeedbackService.getTrainingFeedbacksByTrainingId(training.getId());
        List<TrainingFeedbackDTO> feedbackDTOs = new ArrayList<>();

        for (TrainingFeedback feedback : feedbacks) {
            feedbackDTOs.add(new TrainingFeedbackDTO(feedback));
        }
        return feedbackDTOs;
    }

    @RequestMapping(value = "/newFeedback", method = RequestMethod.POST)
    public void createFeedback(@RequestBody TrainingFeedbackDTO feedbackDTO) {
        TrainingFeedback feedback = new TrainingFeedback(feedbackDTO);
        feedback.setTraining(trainingService.getTrainingById(feedbackDTO.getTrainingId()));
        feedback.setFeedbacker(userService.getUserById(feedbackDTO.getFeedbackerId()));
        trainingFeedbackService.addTrainingFeedback(feedback);
    }

    @RequestMapping(value = "/entry", method = RequestMethod.GET)
    public Entry getEntry(@RequestParam String entryId) {
        long id = Long.parseLong(entryId);
        Entry entry = entryService.getEntryById(id).get();
        System.out.println(entry);
        return entry;
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void modifyTraining(@RequestBody TrainingDTO trainingDTO) {
        Training training = new Training(trainingDTO);
        training.setTrainer(userService.getTrainerById(String.valueOf(trainingDTO.getTrainerId())));
        trainingService.updateTraining(training);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void cancelTraining(@RequestParam String id) {
        trainingService.cancelById(id);
    }
}
