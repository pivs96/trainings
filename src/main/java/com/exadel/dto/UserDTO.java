package com.exadel.dto;

import com.exadel.model.entity.training.Training;
import com.exadel.model.entity.training.TrainingStatus;
import com.exadel.model.entity.user.User;
import com.exadel.model.entity.user.UserRole;

public class UserDTO {

    private String name;
    private String surname;
    private String phone;
    private String email;
    private UserRole role;

    public UserDTO() {
    }

    public UserDTO(User user) {
        this.name = user.getName();
        this.surname = user.getSurname();
        this.phone = user.getPhone();
        this.email = user.getEmail();
        this.role = user.getRole();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
