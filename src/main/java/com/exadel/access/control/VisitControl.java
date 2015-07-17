package com.exadel.access.control;

import com.exadel.dto.TrainingFeedbackDTO;
import com.exadel.model.entity.feedback.TrainingFeedback;
import com.exadel.service.TrainingService;
import com.exadel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value="visitControlBean")
public class VisitControl {

    public final UserService userService;
    public final TrainingService trainingService;



    @Autowired
    public VisitControl(UserService userService, TrainingService trainingService) {
        this.userService = userService;
        this.trainingService = trainingService;
    }
    public boolean isVisit(long id) {
        long currentId=userService.getCurrentId();
        List<Long> participants = trainingService.getParticipants(id);
        if(participants.contains(currentId)) {
           return true;
        }
       return false;
    }

    public boolean isVisiting(TrainingFeedbackDTO feedbackDTO) {
        long id=feedbackDTO.getTrainingId();
        long currentId=userService.getCurrentId();
        List<Long> participants = trainingService.getParticipants(id);
        if(participants.contains(currentId)) {
            return true;
        }
        return false;
    }
}
