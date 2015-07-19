package com.exadel.service;

import com.exadel.model.entity.User;

import java.util.Collection;
import java.util.Optional;

public interface UserService {

    Optional<User> getUserById(long id);

    Optional<User> getUserByEmail(String email);

    Collection<User> getAllUsers();

    /*Collection<User> getUsersByRole();*/

}
