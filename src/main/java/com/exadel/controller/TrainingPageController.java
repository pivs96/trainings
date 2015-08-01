package com.exadel.controller;


import com.exadel.config.SecurityConfig;
import com.exadel.dto.*;
import com.exadel.model.entity.feedback.TrainingFeedback;
import com.exadel.model.entity.ParticipationStatus;
import com.exadel.model.entity.training.Attachment;
import com.exadel.model.entity.training.Participation;
import com.exadel.model.entity.training.Rating;
import com.exadel.model.entity.training.Training;
import com.exadel.model.entity.user.User;
import com.exadel.service.*;
import javafx.util.Pair;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping("/training")
public class TrainingPageController {

    @Autowired
    private TrainingFeedbackService trainingFeedbackService;

    @Autowired
    private TrainingService trainingService;

    @Autowired
    private RatingService ratingService;

    @Autowired
    private UserService userService;

    @Autowired
    private AttachmentService attachmentService;

    @Autowired
    private ParticipationService participationService;

    @PreAuthorize("hasAnyRole('0','1','2')")
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public TrainingDTO getTrainingInfo(@RequestParam String trainingId) {
        return new TrainingDTO(trainingService.getTrainingById(trainingId));
    }

    @PreAuthorize("@trainerControlBean.isOk(#id) or hasRole('0')" )
    @RequestMapping(value = "/participants", method = RequestMethod.GET)
    public List<UserDTO> getParticipants(@RequestParam String trainingId) {
        Training training = trainingService.getTrainingById(trainingId);
        List<UserDTO> userDTOs = new ArrayList<>();
        for (User user : training.getParticipants()) {
            userDTOs.add(new UserDTO(user));
        }
        return userDTOs;
    }

    @PreAuthorize("hasAnyRole('0','1','2')")
    @RequestMapping(value = "/check_participation", method = RequestMethod.GET)
    public ParticipationStatus checkParticipation(@RequestParam String userId, @RequestParam String trainingId) {
        return trainingService.checkParticipation(userId, trainingId);
    }

    @RequestMapping(value = "/participation_time", method = RequestMethod.GET)
    public List<ParticipationDTO> getParticipationTime(@RequestParam String trainingId) {
        Training training = trainingService.getTrainingById(trainingId);
        List<Participation> participations = participationService.getAllParticipationsByTrainingId(training.getId());
        List<ParticipationDTO> participationDTOs = new ArrayList<>();

        for (Participation participation : participations) {
            participationDTOs.add(new ParticipationDTO(participation));
        }
        return participationDTOs;
    }

    @PreAuthorize("hasAnyRole('0','1')")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ParticipationStatus register(@RequestParam String userId, @RequestParam String trainingId) {
        Training training = trainingService.getTrainingById(trainingId);
        User visitor = userService.getUserById(userId);
        training.addParticipant(visitor);
        if (training.getMembersCount() > training.getMembersCountMax())
            return ParticipationStatus.RESERVE;
        else
            return ParticipationStatus.MEMBER;
    }


    @PreAuthorize("hasRole('0')")
    @RequestMapping(value = "/register_visitor", method = RequestMethod.POST)
    public void registerByAdmin(@RequestParam String userId, @RequestParam String trainingId) {
        register(userId, trainingId);
    }

    @PreAuthorize("hasAnyRole('0','1','2')")
    @RequestMapping(value = "/rating", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public double addTrainingRating(@RequestBody RatingDTO ratingDTO) {
        String trainingId = String.valueOf(ratingDTO.getTrainingId());
        ratingService.addRating(new Rating(trainingService.getTrainingById(trainingId),
                userService.getUserById(String.valueOf(ratingDTO.getUserId()))));
        return trainingService.addRating(ratingDTO.getRating(), trainingId);
    }

    @PreAuthorize("hasAnyRole('0','1','2')")
    @RequestMapping(value = "/feedbacks", method = RequestMethod.GET)
    public List<TrainingFeedbackDTO> getFeedbacks(@RequestParam String trainingId) {
        Training training = trainingService.getTrainingById(trainingId);
        List<TrainingFeedback> feedbacks = (List<TrainingFeedback>)
                trainingFeedbackService.getTrainingFeedbacksByTrainingId(training.getId());
        List<TrainingFeedbackDTO> feedbackDTOs = new ArrayList<>();

        for (TrainingFeedback feedback : feedbacks) {
            feedbackDTOs.add(new TrainingFeedbackDTO(feedback));
        }
        return feedbackDTOs;
    }


    @PreAuthorize("@visitControlBean.isVisiting(#feedbackDTO) and hasAnyRole('0','1','2')")
    @RequestMapping(value = "/newFeedback", method = RequestMethod.POST)
    public void createFeedback(@RequestBody TrainingFeedbackDTO feedbackDTO) {
        TrainingFeedback feedback = new TrainingFeedback(feedbackDTO);
        feedback.setTraining(trainingService.getTrainingById(feedbackDTO.getTrainingId()));
        feedback.setFeedbacker(userService.getUserById(feedbackDTO.getFeedbackerId()));
        trainingFeedbackService.addTrainingFeedback(feedback);
    }

    @PreAuthorize("@trainerControlBean.isOk(#id) or hasRole('0') or @visitControlBean.isVisit(#id) and hasAnyRole('1','2')")
    @RequestMapping(value = "attachments", method = RequestMethod.GET)
    public List<AttachmentDTO> getAttachments(@RequestParam String trainingId) {
        Training training = trainingService.getTrainingById(trainingId);
        List<Attachment> attachments = attachmentService.getAllAttachmentsByTrainingId(training.getId());
        List<AttachmentDTO> attachmentDTOs = new ArrayList<>();

        for (Attachment attachment : attachments) {
            attachmentDTOs.add(new AttachmentDTO(attachment));
        }
        return attachmentDTOs;
    }

    @PreAuthorize("@trainerControlBean.isOk(#id) or hasRole('0') or @visitControlBean.isVisit(#id) and hasAnyRole('1','2')")
    @RequestMapping(value = "newAttachment", method = RequestMethod.POST)
    public void createAttachment(@RequestBody AttachmentDTO attachmentDTO) {
        Attachment attachment = new Attachment(attachmentDTO);
        attachment.setTraining(trainingService.getTrainingById(attachmentDTO.getTrainingId()));
        attachmentService.addAttachment(attachment);
    }

    @PreAuthorize("@trainerControlBean.isOk(#id) or hasRole('0') or @visitControlBean.isVisit(#id) and hasAnyRole('1','2')")
    @RequestMapping(value = "editAttachment", method = RequestMethod.PUT)
    public void editAttachment(@RequestBody AttachmentDTO attachmentDTO) {
        Attachment attachment = attachmentService.getAttachmentById(attachmentDTO.getId());
        attachment.setTraining(trainingService.getTrainingById(attachmentDTO.getTrainingId()));
        attachmentService.addAttachment(attachment);
    }

    @PreAuthorize("@trainerControlBean.isOkay(#trainingDTO) or hasRole('0')")
    @RequestMapping(method = RequestMethod.PUT)
    public void modifyTraining(@RequestBody TrainingDTO trainingDTO) {
        Training training = trainingService.getTrainingById(trainingDTO.getId());
        trainingService.updateTraining(training);
    }

    @PreAuthorize("hasRole('0')")
    @RequestMapping(method = RequestMethod.DELETE)
    public void cancelTraining(@RequestParam String id) {
        trainingService.cancelById(id);
    }
}
