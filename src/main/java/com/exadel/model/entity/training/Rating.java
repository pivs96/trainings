package com.exadel.model.entity.training;

import com.exadel.model.entity.training.Training;
import com.exadel.model.entity.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "ratings")
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "training_id", nullable = false)
    private Training training;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Rating() {
    }

    public Rating(Training training, User user) {
        this.training = training;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Training getTraining() {
        return training;
    }

    public void setTraining(Training training) {
        this.training = training;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
