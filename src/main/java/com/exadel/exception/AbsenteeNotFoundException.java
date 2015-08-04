package com.exadel.exception;

public class AbsenteeNotFoundException extends RuntimeException {
    private String absenteeId;

    public AbsenteeNotFoundException(String absenteeId) {
        this.absenteeId = absenteeId;
    }

    public String getAbsenteeId() {
        return absenteeId;
    }
}
