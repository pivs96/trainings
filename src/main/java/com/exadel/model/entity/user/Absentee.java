package com.exadel.model.entity.user;

import com.exadel.dto.AbsenteeDTO;
import com.exadel.model.entity.training.Entry;

import javax.persistence.*;

@Entity
@Table(name = "absentees")
public class Absentee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String reason;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "entry_id", nullable = false)
    private Entry entry;

    public Absentee() {
    }

    public Absentee(AbsenteeDTO absenteeDTO) {
        this.id = absenteeDTO.getId();
        this.reason = absenteeDTO.getReason();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Entry getEntry() {
        return entry;
    }

    public void setEntry(Entry entry) {
        this.entry = entry;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
