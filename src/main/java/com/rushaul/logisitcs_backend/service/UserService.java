package com.rushaul.logisitcs_backend.service;

import com.rushaul.logisitcs_backend.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User createUser(User user);
    Optional<User> getUserById(Long id);
    List<User> getAllUsers();
    User updateUser(Long id, User user);
    User deleteUser(Long id);
    Optional<User> findByUsername(String username);
}
