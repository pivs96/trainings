package com.exadel.model.entity.training;

import java.util.List;
import static com.exadel.controller.TrainingsController.*;

public class Journal {
    private List attendance;

    public List getAttendance() {
        return attendance;
    }

    public void setAttendance(List attendance) {
        this.attendance = attendance;
    }

    public Journal(int id) {

    }
}
