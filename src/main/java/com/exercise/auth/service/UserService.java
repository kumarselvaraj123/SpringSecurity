package com.exercise.auth.service;

import com.exercise.auth.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
