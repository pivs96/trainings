package com.exadel.dto;

import com.exadel.model.entity.training.Entry;

import java.util.Comparator;
import java.util.Date;

public class EntryDTO implements Comparable<EntryDTO> {
    private long id;
    private String city;
    private String unit;
    private Date beginTime;
    private Date endTime;
    private long trainingId;
    private int dayOfWeek;

    public EntryDTO() {
    }

    public EntryDTO(Entry entry) {
        this.id = entry.getId();
        this.city = entry.getCity();
        this.unit = entry.getUnit();
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
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
        return (int)(this.getBeginTime().getTime() - entry.getBeginTime().getTime());
    }
}
