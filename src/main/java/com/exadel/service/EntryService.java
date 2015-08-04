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

    List<Entry> findFutureEntriesByTrainingId(Date time, long trainingId);

    List<Entry> findEntriesForJournal(Date beginDay, Date endDay, long trainingId);

    Entry findNextEntryByTrainingId(Date time, long trainingId);

    Optional<Entry> addEntry(Entry entry);

    void deleteEntry(long id);
}
