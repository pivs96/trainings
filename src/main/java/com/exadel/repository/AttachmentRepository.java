package com.exadel.repository;

import com.exadel.model.entity.training.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface AttachmentRepository extends JpaRepository<Attachment, Long> {
    List<Attachment> findByTrainingId(long trainingId);
}
