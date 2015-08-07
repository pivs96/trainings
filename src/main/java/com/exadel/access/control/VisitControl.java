package com.exadel.access.control;

import com.exadel.dto.AttachmentDTO;
import com.exadel.dto.TrainingFeedbackDTO;
import com.exadel.model.entity.feedback.TrainingFeedback;
import com.exadel.model.entity.training.Entry;
import com.exadel.model.entity.training.Training;
import com.exadel.service.TrainingService;
import com.exadel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component(value = "visitControlBean")
public class VisitControl {

    public final UserService userService;
    public final TrainingService trainingService;

    @Autowired
    public VisitControl(UserService userService, TrainingService trainingService) {
        this.userService = userService;
        this.trainingService = trainingService;
    }

    public boolean isVisit(AttachmentDTO attachmentDTO) {
        long currentId = userService.getCurrentId();
        List<Long> participants = trainingService.getParticipants(attachmentDTO.getTrainingId());
        return participants.contains(currentId);
    }

    public boolean isVisit(long trainingId) {
        long currentId = userService.getCurrentId();
        List<Long> participants = trainingService.getParticipants(trainingId);
        return participants.contains(currentId);
    }

    public boolean isVisiting(TrainingFeedbackDTO feedbackDTO) {
        long id = feedbackDTO.getTrainingId();
        long currentId = userService.getCurrentId();
        List<Long> participants = trainingService.getParticipants(id);
        return participants.contains(currentId);
    }

    public boolean isStarted(String id, boolean isRepeated) {
        if(isRepeated) {
            return true;
        }
        Training training = trainingService.getTrainingById(id);
        List<Entry> list =training.getEntries();
            Entry firstEntry = list.get(0);
            Date beginDate = firstEntry.getBeginTime();
            Date currentDate = new Date();
            if(beginDate.before(currentDate))
                return false;
            else
                return true;

    }
}
