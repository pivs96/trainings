package com.exadel.service;

import com.exadel.model.entity.training.Attachment;

import java.util.List;
import java.util.Optional;

public interface AttachmentService {

    List<Attachment> getAllAttachmentsByEntryId(long entryId);

    Optional<Attachment> addAttachment(Attachment attachment);
}
