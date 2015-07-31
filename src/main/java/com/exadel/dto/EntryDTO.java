package com.exadel.dto;

import com.exadel.model.entity.training.Entry;

import java.util.Date;

public class EntryDTO {
    private long id;
    private String place;
    private Date beginTime;
    private Date endTime;
    private long trainingId;

    public EntryDTO() {
    }

    public EntryDTO(Entry entry) {
        this.id = entry.getId();
        this.place = entry.getPlace();
        this.beginTime = entry.getBeginTime();
        this.endTime = entry.getEndTime();
        this.trainingId = entry.getTraining().getId();
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
}
