package com.exadel.model.entity.training;

import com.exadel.dto.ParticipationDTO;
import com.exadel.model.entity.training.Training;
import com.exadel.model.entity.user.User;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "training_users")
public class Participation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "training_id", nullable = false)
    private Training training;

    @Column(name = "begin_day")
    private Date beginDay;

    @Column(name = "end_day")
    private Date endDay;

    public Participation() {
    }

    public Participation(ParticipationDTO participationDTO) {
        this.id = participationDTO.getId();
        this.beginDay = participationDTO.getBeginDay();
        this.endDay = participationDTO.getEndDay();
    }

    public Participation(User user, Training training, Date beginDay, Date endDay) {
        this.user = user;
        this.training = training;
        this.beginDay = beginDay;
        this.endDay = endDay;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Training getTraining() {
        return training;
    }

    public void setTraining(Training training) {
        this.training = training;
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
