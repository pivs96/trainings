package com.exadel.dto;

import com.exadel.model.entity.training.Participation;

import java.util.Date;

public class ParticipationDTO {

    private long id;
    private long userId;
    private long trainingId;
    private Date beginDay;
    private Date endDay;

    public ParticipationDTO() {
    }

    public ParticipationDTO(Participation participation) {
        this.id = participation.getId();
        this.userId = participation.getUser().getId();
        this.trainingId = participation.getTraining().getId();
        this.beginDay = participation.getBeginDay();
        this.endDay = participation.getEndDay();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getTrainingId() {
        return trainingId;
    }

    public void setTrainingId(long trainingId) {
        this.trainingId = trainingId;
    }

    public Date getBeginDay() {
        return beginDay;
    }

    public void setBeginDay(Date beginDay) {
        this.beginDay = beginDay;
    }

    public Date getEndDay() {
        return endDay;
    }

    public void setEndDay(Date endDay) {
        this.endDay = endDay;
    }
}
