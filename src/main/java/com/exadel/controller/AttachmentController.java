package com.exadel.controller;

import com.exadel.dto.AttachmentDTO;
import com.exadel.model.entity.training.Attachment;
import com.exadel.model.entity.training.Training;
import com.exadel.service.AttachmentService;
import com.exadel.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/training")
public class AttachmentController {
    @Autowired
    private TrainingService trainingService;
    @Autowired
    private AttachmentService attachmentService;

    @PreAuthorize("@trainerControlBean.isTrainer(#trainingId) or hasRole('0') or @visitControlBean.isVisit(#trainingId) and hasAnyRole('1','2')")
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

    @PreAuthorize("@trainerControlBean.isTrainerByAttachments(#attachmentDTOs) or hasRole('0')")
    @RequestMapping(value = "attachments", method = RequestMethod.POST)
    public void createAttachmentLinks(@RequestBody List<AttachmentDTO> attachmentDTOs) {
        if (attachmentDTOs != null) {
            for (AttachmentDTO attachmentDTO : attachmentDTOs) {
                attachmentDTO.setName(attachmentDTO.getName() + " (external link)");
                Attachment attachment = new Attachment(attachmentDTO);
                attachment.setTraining(trainingService.getTrainingById(attachmentDTO.getTrainingId()));
                attachmentService.addAttachmentLink(attachment);
            }
        }
    }

    @PreAuthorize("@trainerControlBean.isTrainerByAttachmentId(#id) or hasRole('0')")
    @RequestMapping(value = "attachment", method = RequestMethod.DELETE)
    public void deleteAttachment(@RequestParam String id) {
        Attachment attachment = attachmentService.getAttachmentById(id);
        attachmentService.deleteAttachment(attachment.getId());
    }
}
