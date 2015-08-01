package com.exadel.dto;

import com.exadel.model.entity.user.Absentee;

public class AbsenteeDTO {

    private long id;
    private long userId;
    private long entryId;
    private String reason;

    public AbsenteeDTO() {
    }

    public AbsenteeDTO(Absentee absentee) {
        this.id = absentee.getId();
        this.userId = absentee.getUser().getId();
        this.reason = absentee.getReason();
        this.entryId = absentee.getEntry().getId();
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public long getEntryId() {
        return entryId;
    }

    public void setEntryId(long entryId) {
        this.entryId = entryId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
