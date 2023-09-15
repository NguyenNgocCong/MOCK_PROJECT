package com.mock_project.mock_project.service;

import com.mock_project.mock_project.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> getByUsername(String username);
    List<User> getAllUserOnline();
    Optional<User> getUserById(Long id);
    User updateUserOnline(User user);
    void handleTokenExpired(User user);
}
