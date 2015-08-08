package com.exadel.access.control;

import com.exadel.dto.AbsenteeDTO;
import com.exadel.dto.AttachmentDTO;
import com.exadel.dto.EntryDTO;
import com.exadel.dto.TrainingDTO;
import com.exadel.model.entity.training.Attachment;
import com.exadel.model.entity.training.Entry;
import com.exadel.model.entity.training.Training;
import com.exadel.service.AttachmentService;
import com.exadel.service.EntryService;
import com.exadel.service.TrainingService;
import com.exadel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "trainerControlBean")
public class TrainerControl {

    public final UserService userService;
    public final TrainingService trainingService;
    public final EntryService entryService;
    public final AttachmentService attachmentService;

    @Autowired
    public TrainerControl(UserService userService, TrainingService trainingService,
                          EntryService entryService, AttachmentService attachmentService) {
        this.userService = userService;
        this.trainingService = trainingService;
        this.entryService = entryService;
        this.attachmentService = attachmentService;
    }

    public boolean isTrainer(long trainingId) {
        if (userService.getCurrentId() == trainingService.getTrainerId(trainingId))
            return true;
        else
            return false;
    }

    public boolean isTrainer(String trainingId) {
        if (userService.getCurrentId() ==
                trainingService.getTrainingById(trainingId).getTrainer().getId())
            return true;
        else
            return false;
    }

    public boolean isTrainer(TrainingDTO trainingDTO) {
        long id = trainingDTO.getTrainer().getId();
        if (userService.getCurrentId() == trainingService.getTrainerId(id))
            return true;
        else
            return false;
    }

    public boolean isTrainerByAttachments(List<AttachmentDTO> attachmentDTOs) {
        if (attachmentDTOs == null)
            return false;

        for (AttachmentDTO attachmentDTO : attachmentDTOs) {
            if (attachmentDTO != null) {
                long trainingId = attachmentDTO.getTrainingId();
                if (userService.getCurrentId() == trainingService.getTrainerId(trainingId))
                    return true;
            }
        }
        return false;
    }

    public boolean isTrainerByEntryId(String entryId) {
        Entry entry = entryService.getEntryById(entryId);
        Training training = entry.getTraining();
        long id = training.getId();
        return isTrainer(id);
    }

    public boolean isTrainer(EntryDTO entryDTO) {
        long id = entryDTO.getTrainingId();
        return isTrainer(id);
    }

    public boolean isTrainer(List<EntryDTO> entryDTOs) {
        if (entryDTOs == null)
            return false;

        for (EntryDTO entryDTO: entryDTOs) {
            if (entryDTO != null) {
                long id = entryDTOs.get(0).getTrainingId();
                if (userService.getCurrentId() == trainingService.getTrainerId(id))
                    return true;
            }
        }

        return false;
    }

    public boolean isTrainer(AbsenteeDTO absenteeDTO) {
        Entry entry = entryService.getEntryById(absenteeDTO.getEntryId());
        if (entry == null)
            return false;

        Training training = trainingService.getTrainingById(entry.getTraining().getId());
        if (training == null)
            return false;

        return isTrainer(training.getId());
    }

    private boolean isTrainerByAttachment(String id) {
        Attachment attachment = attachmentService.getAttachmentById(id);
        return isTrainer(attachment.getTraining().getId());
    }
}
