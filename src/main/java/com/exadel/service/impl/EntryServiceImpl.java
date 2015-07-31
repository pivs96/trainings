package com.exadel.service.impl;

import com.exadel.exception.EntryNotFoundException;
import com.exadel.model.entity.training.Entry;
import com.exadel.repository.EntryRepository;
import com.exadel.service.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackFor = {EntryNotFoundException.class})
public class EntryServiceImpl implements EntryService{
    @Autowired
    private EntryRepository entryRepository;

    @Override
    public Entry getEntryById(String id) {
        try {
            long entryId = Long.parseLong(id);
            return getEntryById(entryId);
        } catch (NumberFormatException ex) {
            throw new EntryNotFoundException(id);
        }
    }

    @Override
    public Entry  getEntryById(long id) {
        Entry entry = entryRepository.findOne(id);
        if (entry != null) {
            return entry;
        }
        else {
            throw new EntryNotFoundException(String.valueOf(id));
        }
    }

    @Override
    public List<Entry> getAllEntriesByTrainingId(long trainingId) {
        return entryRepository.findByTrainingId(trainingId);
    }

    //@Override
    public List<Entry> getAllEntriesByTrainingId(long trainingId, Pageable pageable) {
        return entryRepository.findByTrainingId(trainingId, pageable);
    }

    @Override
    public List<Entry> findFutureEntriesByTrainingId(Date time, long trainingId) {
        return entryRepository.findByTrainingIdAndBeginTimeAfter(trainingId, time);
    }

    @Override
    public Entry findNextEntryByTrainingId(Date time, long trainingId) {
        return entryRepository.findOneByTrainingIdAndBeginTimeAfter(trainingId, time);
    }

    //@Override
    public List<Entry> findFutureEntriesByTrainingId(Date time, long trainingId, Pageable pageable) {
        return entryRepository.findByTrainingIdAndBeginTimeAfter(trainingId, time, pageable);
    }

    @Override
    public Optional<Entry> addEntry(Entry entry) {
        return Optional.ofNullable(entryRepository.save(entry));
    }
}
