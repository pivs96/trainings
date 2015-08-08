package com.exadel.service;

import com.exadel.model.entity.training.Attachment;

import java.util.List;
import java.util.Optional;

public interface AttachmentService {

    List<Attachment> getAllAttachmentsByTrainingId(long trainingId);

    void addAttachmentFile(Attachment attachment);

    void addAttachmentLink(Attachment attachment);

    Attachment getAttachmentById(String id);

    Attachment getAttachmentById(long id);

    void deleteAttachment(long id);
}
