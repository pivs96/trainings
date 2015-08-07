package com.exadel.service;

import com.exadel.model.entity.training.Entry;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface EntryService {
    List<Entry> getAllEntries();

    Entry getEntryById(String id);

    Entry getEntryById(long id);

    List<Entry> getAllEntriesByTrainingId(long trainingId);

    List<Entry> findFutureEntriesByTrainingId(Date time, long trainingId);

    List<Entry> findEntriesForJournal(Date beginDay, Date endDay, long trainingId);

    List<Entry> findEntriesForJournal(Date endDay, long trainingId);

    Entry findNextEntryByTrainingId(Date time, long trainingId);

    Optional<Entry> addEntry(Entry entry);

    void deleteEntry(long id);

    List<Entry> getBetweenDates(Date before, Date after);
}
