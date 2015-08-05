package com.exadel.service.impl;

import com.exadel.exception.AbsenteeNotFoundException;
import com.exadel.model.entity.user.Absentee;
import com.exadel.repository.AbsenteeRepository;
import com.exadel.service.AbsenteeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = {AbsenteeNotFoundException.class})
public class AbsenteeServiceImpl implements AbsenteeService {
    @Autowired
    private AbsenteeRepository absenteeRepository;

    @Override
    public List<Absentee> getAllAbsenteesByEntryId(long entryId) {
        return absenteeRepository.findByEntryId(entryId);
    }

    @Override
    public Absentee addAbsentee(Absentee absentee) {
        return absenteeRepository.save(absentee);
    }

    @Override
    @Modifying
    public void updateAbsentee(Absentee absentee) {
        Absentee oldAbsentee = getAbsenteeById(absentee.getId());
        oldAbsentee.update(absentee);
    }

    @Override
    public Absentee getAbsenteeById(String id) {
        try {
            long absenteeId = Long.parseLong(id);
            return getAbsenteeById(absenteeId);
        } catch (NumberFormatException ex) {
            throw new AbsenteeNotFoundException(id);
        }
    }

    @Override
    public Absentee getAbsentee(long userId, long entryId) {
        return absenteeRepository.findByUserIdAndEntryId(userId, entryId);
    }

    @Override
    public Absentee getAbsenteeById(long id) {
        Absentee absentee = absenteeRepository.findOne(id);
        if (absentee != null) {
            return absentee;
        }
        else {
            throw new AbsenteeNotFoundException(String.valueOf(id));
        }
    }

    @Override
    public void deleteAbsentee(String id) {
        absenteeRepository.delete(getAbsenteeById(id).getId());
    }
}
