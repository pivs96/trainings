package com.exadel.service;

import com.exadel.model.entity.training.Entry;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface EntryService {

    Entry getEntryById(String id);

    Entry getEntryById(long id);

    List<Entry> getAllEntriesByTrainingId(long trainingId);

    //List<Entry> getAllEntriesByTrainingId(long trainingId, Pageable pageable);

    List<Entry> findFutureEntriesByTrainingId(Date time, long trainingId);

    Entry findNextEntryByTrainingId(Date time, long trainingId);

    //List<Entry> findFutureEntriesByTrainingId(Date time, long trainingId, Pageable pageable);

    Optional<Entry> addEntry(Entry entry);
}
