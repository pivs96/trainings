package com.exadel.model.entity.training;

import com.exadel.dto.EntryDTO;
import com.exadel.model.entity.user.User;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "entries")
public class Entry {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String place;

    @Column(name = "begin_time")
    private Date beginTime;
    @Column(name = "end_time")
    private Date endTime;

    @ManyToOne
    @JoinColumn(name = "training_id", nullable = false)
    private Training training;

    @ManyToMany
    @JoinTable(name = "absentees", joinColumns = @JoinColumn(name = "entry_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> absentees;

    public Entry() {
    }

    public Entry(EntryDTO entryDTO) {
        this.id = entryDTO.getId();
        this.place = entryDTO.getPlace();
        this.beginTime = entryDTO.getBeginTime();
        this.endTime = entryDTO.getEndTime();
    }

    public void updateEntry(EntryDTO entryDTO) {
        this.place = entryDTO.getPlace();
        this.beginTime = entryDTO.getBeginTime();
        this.endTime = entryDTO.getEndTime();
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

    public Training getTraining() {
        return training;
    }

    public void setTraining(Training training) {
        this.training = training;
    }

    public List<User> getAbsentees() {
        return absentees;
    }

    public void setAbsentees(List<User> absentees) {
        this.absentees = absentees;
    }
}
