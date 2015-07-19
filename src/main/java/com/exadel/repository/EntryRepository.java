package com.exadel.repository;

import com.exadel.model.entity.Entry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EntryRepository extends JpaRepository<Entry, Long> {

    List<Entry> findAll();

    void delete(Entry deleted);

    Entry save(Entry persisted);
}
