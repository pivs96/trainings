package com.exadel.repository;

import com.exadel.model.entity.user.Absentee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface AbsenteeRepository extends JpaRepository<Absentee, Long> {
    List<Absentee> findByEntryId(long entryId);

    Absentee findByUserIdAndEntryId(long userId, long entryId);
}
