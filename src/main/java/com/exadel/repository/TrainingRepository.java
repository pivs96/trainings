package com.exadel.repository;

import com.exadel.model.entity.Training;
import com.exadel.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TrainingRepository extends JpaRepository<Training, Long> {

    Page<Training> findAll(Pageable pageable);

    void delete(Training deleted);

    Training save(Training persisted);
}
