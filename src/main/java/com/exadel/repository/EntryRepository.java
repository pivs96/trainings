package com.exadel.repository;

import com.exadel.model.entity.training.Entry;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface EntryRepository extends JpaRepository<Entry, Long> {
    List<Entry> findByTrainingIdAndBeginTimeAfter(long trainingId, Date beginTime);

    List<Entry> findByTrainingIdAndEndTimeAfterAndBeginTimeBefore(long trainingId,
                                                                  Date endTime, Date beginTime);

    List<Entry> findByTrainingIdAndBeginTimeBefore(long trainingId, Date beginTime);

    Entry findFirstByTrainingIdAndBeginTimeAfter(long trainingId, Date beginTime);

    List<Entry> findByTrainingId(long trainingId);

    @Query(value = "SELECT * FROM entries WHERE begin_time >= ?1 AND begin_time < ?2", nativeQuery = true)
    List<Entry> findBetweenDates(Date before, Date after);
}