package com.exadel.model.entity.user;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "0")
public class Admin extends Employee {

    public Admin() {
        super.setRole(UserRole.ADMIN);
    }
}
