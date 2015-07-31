package com.exadel.repository;

import com.exadel.model.entity.training.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttachmentRepository extends JpaRepository<Attachment, Long> {
    List<Attachment> findByEntryId(long trainingId);
}
