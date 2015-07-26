package com.exadel.service.impl;

import com.exadel.model.entity.training.Entry;
import com.exadel.model.entity.training.TrainingStatus;
import com.exadel.service.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@EnableScheduling
public class ScheduleService {
    @Autowired
    private SmtpMailSender smtpMailSender;
    @Autowired
    private EntryService entryService;
    @Autowired
    private EmailMessages emailMessages;

    @Scheduled(fixedRate = 3600*1000)
    public void beforeDay(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(calendar.HOUR, 23);
        Date before = calendar.getTime();
        calendar.add(calendar.HOUR, 1);
        Date after = calendar.getTime();
        List<Entry> entries = entryService.getBetweenDates(before, after);

        for(Entry entry: entries) {
            if (entry.getTraining().isRepeated()==false && entry.getTraining().getStatus()== TrainingStatus.APPROVED) {
                smtpMailSender.sendToParticipants(entry, "Training", emailMessages.beforeDay(entry));
            }
        }
    }

    @Scheduled(fixedRate = 60*1000)
    public void beforeHour(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(calendar.MINUTE, 59);
        Date before = calendar.getTime();
        calendar.add(calendar.MINUTE, 1);
        Date after = calendar.getTime();
        List<Entry> entries = entryService.getBetweenDates(before, after);

        for(Entry entry: entries) {
            if (entry.getTraining().getStatus()== TrainingStatus.APPROVED) {
                smtpMailSender.sendToParticipants(entry.getTraining(), "Training", emailMessages.beforeHour(entry));
            }
        }
    }

    @Scheduled(fixedRate = 60*3000)
    public void before3Hours(){

    }
}
