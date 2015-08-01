package com.exadel.service.impl;

import com.exadel.model.entity.user.Absentee;
import com.exadel.repository.AbsenteeRepository;
import com.exadel.service.AbsenteeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AbsenteeServiceImpl implements AbsenteeService {
    @Autowired
    private AbsenteeRepository absenteeRepository;

    @Override
    public List<Absentee> getAllAbsenteesByEntryId(long entryId) {
        return absenteeRepository.findByEntryId(entryId);
    }
}
