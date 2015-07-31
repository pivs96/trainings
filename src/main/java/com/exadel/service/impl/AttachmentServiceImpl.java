package com.exadel.service.impl;

import com.exadel.exception.AttachmentNotFoundException;
import com.exadel.model.entity.training.Attachment;
import com.exadel.repository.AttachmentRepository;
import com.exadel.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackFor = {AttachmentNotFoundException.class})
public class AttachmentServiceImpl implements AttachmentService {
    @Autowired
    private AttachmentRepository attachmentRepository;

    @Override
    public List<Attachment> getAllAttachmentsByTrainingId(long trainingId) {
        return attachmentRepository.findByTrainingId(trainingId);
    }

    @Override
    public void addAttachment(Attachment attachment) {
        attachmentRepository.save(attachment);
    }

    @Override
    public Attachment getAttachmentById(String id) {
        try {
            long attachId = Long.parseLong(id);
            return getAttachmentById(attachId);
        } catch (NumberFormatException ex) {
            throw new AttachmentNotFoundException(id);
        }
    }

    @Override
    public Attachment getAttachmentById(long id) {
        Attachment attachment = attachmentRepository.findOne(id);
        if (attachment != null) {
            return attachment;
        }
        else {
            throw new AttachmentNotFoundException(String.valueOf(id));
        }
    }
}
