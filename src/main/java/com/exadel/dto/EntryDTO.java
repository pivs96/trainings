package com.exadel.dto;

import com.exadel.model.entity.training.Entry;
import com.exadel.model.entity.user.Absentee;
import com.exadel.model.entity.user.User;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class EntryDTO implements Comparable<EntryDTO> {
    private long id;
    private String place;
    private Date beginTime;
    private Date endTime;
    private long trainingId;
    private int dayOfWeek;

    private List<AbsenteeDTO> absentees;

    private String eventDescription;

    public EntryDTO() {
    }

    public EntryDTO(Entry entry) {
        this.id = entry.getId();
        this.place = entry.getPlace();
        this.beginTime = entry.getBeginTime();
        this.endTime = entry.getEndTime();
        this.trainingId = entry.getTraining().getId();

        this.absentees = new ArrayList<>();
        for (Absentee absentee : entry.getAbsentees()) {
            this.absentees.add(new AbsenteeDTO(absentee));
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public long getTrainingId() {
        return trainingId;
    }

    public void setTrainingId(long trainingId) {
        this.trainingId = trainingId;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    @Override
    public int compareTo(EntryDTO entry) {
        return (int) (this.getBeginTime().getTime() - entry.getBeginTime().getTime());
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public List<AbsenteeDTO> getAbsentees() {
        return absentees;
    }

    public void setAbsentees(List<AbsenteeDTO> absentees) {
        this.absentees = absentees;
    }
}
