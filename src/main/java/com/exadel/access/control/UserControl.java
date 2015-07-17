package com.exadel.access.control;

import com.exadel.service.TrainingService;
import com.exadel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component(value="userControlBean")
public class UserControl {

    public final UserService userService;
    public final TrainingService trainingService;



    @Autowired
    public UserControl(UserService userService, TrainingService trainingService) {
        this.userService = userService;
        this.trainingService = trainingService;
    }
    public boolean isMyAccount(long id) {
        if (userService.getCurrentId()==id)
            return true;
        else
            return false;
    }
}
