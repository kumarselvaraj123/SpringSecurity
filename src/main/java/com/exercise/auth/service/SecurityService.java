package com.exercise.auth.service;

public interface SecurityService {
    String findLoggedInUsername();

    void automaticlogin(String username, String password);
}
