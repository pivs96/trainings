package com.exadel.repository;

import com.exadel.model.entity.training.Entry;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Date;
import java.util.List;

public interface EntryRepository extends JpaRepository<Entry, Long> {
    List<Entry> findByTrainingIdAndBeginTimeAfter(long trainingId, Date beginTime);

    List<Entry> findByTrainingIdAndBeginTimeAfterAndEndTimeBefore(long trainingId,
                                                                  Date beginTime, Date endTime);

    Entry findFirstByTrainingIdAndBeginTimeAfter(long trainingId, Date beginTime);

    List<Entry> findByTrainingId(long trainingId);
}