package com.exadel.controller;

import com.exadel.dto.AttachmentDTO;
import com.exadel.dto.EntryDTO;
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

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/entry")
public class EntryController {
    @Autowired
    private EntryService entryService;

}
