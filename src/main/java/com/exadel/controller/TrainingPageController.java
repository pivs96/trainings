package com.exadel.controller;

import com.exadel.dto.*;
import com.exadel.model.entity.training.Attachment;
import com.exadel.model.entity.training.Entry;
import com.exadel.model.entity.training.Rating;
import com.exadel.model.entity.training.Training;
import com.exadel.model.entity.feedback.TrainingFeedback;
import com.exadel.model.entity.user.User;
import com.exadel.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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

    @Autowired
    private AttachmentService attachmentService;

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public TrainingDTO getTrainingInfo(@RequestParam String id) {
        return new TrainingDTO(trainingService.getTrainingById(id));
    }

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
        User visitor = userService.getUserById(userId);
        training.addParticipant(visitor);
        trainingService.addTraining(training);

        if (training.getMembersCount() > training.getMembersCountMax())
            return Participation.RESERVE;
        else
            return Participation.MEMBER;
    }

    @RequestMapping(value = "/register_visitor", method = RequestMethod.POST)
    public void registerByAdmin(@RequestParam String userId, @RequestParam String trainingId) {
        register(userId, trainingId);
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

    @RequestMapping(value = "/entries", method = RequestMethod.GET)
    public List<EntryDTO> getEntries(@RequestParam String trainingId,
                                     @RequestParam(required = false) Boolean future) {
        Training training = trainingService.getTrainingById(trainingId);
        List<Entry> entries;

        if (future == null || !future)
            entries = entryService.getAllEntriesByTrainingId(training.getId());
        else
            entries = entryService.findFutureEntriesByTrainingId(new Date(), training.getId());

        List<EntryDTO> entryDTOs = new ArrayList<>();

        for (Entry entry : entries) {
            entryDTOs.add(new EntryDTO(entry));
        }
        return entryDTOs;
    }

    @RequestMapping(value = "/nextEntry", method = RequestMethod.GET)
    public EntryDTO getNextEntry(@RequestParam String trainingId) {
        Training training = trainingService.getTrainingById(trainingId);
        Entry entry = entryService.findNextEntryByTrainingId(new Date(), training.getId());
        return new EntryDTO(entry);
    }

    @RequestMapping(value = "/createEntries", method = RequestMethod.POST)
    public void createEntries(@RequestParam(required = false) Long end,
                              @RequestBody List<EntryDTO> entryDTOs) {
        Training training = new Training();
        training.setId(entryDTOs.get(0).getTrainingId());

        for (EntryDTO entryDTO : entryDTOs) {
            Entry entry = new Entry(entryDTO);
            entry.setTraining(training);
            entryService.addEntry(entry);
        }

        if (end != null) {
            Date endDay = new Date(end);
            Queue<EntryDTO> duplicates = new LinkedList<>(entryDTOs);
            EntryDTO entryDTO = duplicates.remove();
            entryDTO.setBeginTime(addWeekToDate(entryDTO.getBeginTime()));

            while (entryDTO.getBeginTime().before(endDay)) {
                entryDTO.setEndTime(addWeekToDate(entryDTO.getEndTime()));
                Entry entry = new Entry(entryDTO);
                entry.setTraining(training);
                entryService.addEntry(entry);
                duplicates.add(entryDTO);

                entryDTO = duplicates.remove();
                entryDTO.setBeginTime(addWeekToDate(entryDTO.getBeginTime()));
            }
        }
    }

    public static Date addWeekToDate(final Date date) {
        Date newDate = new Date(date.getTime());

        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(newDate);
        calendar.add(Calendar.DATE, 7);
        newDate.setTime(calendar.getTime().getTime());

        return newDate;
    }

    @RequestMapping(value = "attachments", method = RequestMethod.GET)
    public List<AttachmentDTO> getAttachments(@RequestParam String id) {
        Training training = trainingService.getTrainingById(id);
        List<Attachment> attachments = attachmentService.getAllAttachmentsByTrainingId(training.getId());
        List<AttachmentDTO> attachmentDTOs = new ArrayList<>();

        for (Attachment attachment : attachments) {
            attachmentDTOs.add(new AttachmentDTO(attachment));
        }
        return attachmentDTOs;
    }

    @RequestMapping(value = "newAttachment", method = RequestMethod.POST)
    public void createAttachment(@RequestBody AttachmentDTO attachmentDTO) {
        Attachment attachment = new Attachment(attachmentDTO);
        attachment.setTraining(trainingService.getTrainingById(attachmentDTO.getTrainingId()));
        attachmentService.addAttachment(attachment);
    }

    @RequestMapping(value = "editAttachment", method = RequestMethod.PUT)
    public void editAttachment(@RequestBody AttachmentDTO attachmentDTO) {
        Attachment attachment = attachmentService.getAttachmentById(attachmentDTO.getId());
        attachment.setTraining(trainingService.getTrainingById(attachmentDTO.getTrainingId()));
        attachmentService.addAttachment(attachment);
    }
}
