package com.exadel.controller;

import com.exadel.model.entity.feedback.TrainingFeedback;
import com.exadel.model.entity.training.Attachment;
import com.exadel.model.entity.training.Entry;
import com.exadel.model.entity.training.Training;
import com.exadel.model.entity.user.User;
import com.exadel.service.EntryService;
import com.exadel.service.TrainingFeedbackService;
import com.exadel.service.TrainingService;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/entry")
public class EntryController {
    @Autowired
    private EntryService entryService;

    @RequestMapping(value = "attachments", method = RequestMethod.GET)
    public List<Attachment> getAttachments(@RequestParam String id) {
        Entry entry = new Entry();//trainingService.getTrainingById(id);
        return entry.getAttachments();
    }

    @RequestMapping(value = "/entry", method = RequestMethod.GET)
    public Entry getEntry(@RequestParam String entryId) {
        long id = Long.parseLong(entryId);
        Entry entry = entryService.getEntryById(id).get();
        System.out.println(entry);
        return entry;
    }
}
