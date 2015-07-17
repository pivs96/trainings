package com.exadel.controller;


import com.exadel.model.entity.training.Journal;
import com.exadel.model.entity.training.Training;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/trainings/journal")
public class JournalController {
    static List<Training> trainings = new ArrayList<Training>();

    @PreAuthorize("hasRole('0') or @trainerControlBean.isOk(#id)")
    public List getJournal(@RequestParam(value="trainingId") String id) {
        return new Journal(Integer.parseInt(id)).getAttendance();
    }
}
