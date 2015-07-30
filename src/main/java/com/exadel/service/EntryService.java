package com.exadel.service;

import com.exadel.model.entity.training.Entry;

import java.util.Collection;
import java.util.Optional;

public interface EntryService {

    Optional<Entry> getEntryById(long id);

    Collection<Entry> getAllEntriesByTrainingId(long trainingId);

    Collection<Entry> getAllEntries();

    Optional<Entry> addEntry(Entry training);
}
