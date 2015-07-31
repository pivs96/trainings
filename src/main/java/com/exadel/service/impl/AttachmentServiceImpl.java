/*
package com.exadel.service.impl;

import com.exadel.model.entity.training.Attachment;
import com.exadel.repository.AttachmentRepository;
import com.exadel.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AttachmentServiceImpl implements AttachmentService {
    private final AttachmentRepository attachmentRepository;

    @Autowired
    public AttachmentServiceImpl(AttachmentRepository attachmentRepository) {
        this.attachmentRepository = attachmentRepository;
    }

    @Override
    public List<Attachment> getAllAttachmentsByEntryId(long entryId) {
        return null;
    }

    @Override
    public Optional<Attachment> addAttachment(Attachment attachment) {
        return attachmentRepository.saveAndFlush(attachment);
    }
}
*/
