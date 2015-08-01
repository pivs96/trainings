package com.exadel.service;

import com.exadel.model.entity.user.Absentee;

import java.util.List;

public interface AbsenteeService {
    List<Absentee> getAllAbsenteesByEntryId(long entryId);
}
