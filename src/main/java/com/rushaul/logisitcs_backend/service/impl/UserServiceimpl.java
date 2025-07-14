package com.rushaul.logisitcs_backend.service.impl;

import com.rushaul.logisitcs_backend.model.User;
import com.rushaul.logisitcs_backend.repository.UserRepository;
import com.rushaul.logisitcs_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceimpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceimpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(Long id, User userDetails) {
        return userRepository.findById(id).map(user -> {
            user.setName(userDetails.getName());
            user.setPhone_number(userDetails.getPhone_number());
            user.setAddress(userDetails.getAddress());
            user.setEmail(userDetails.getEmail());
            return userRepository.save(user);
        }).orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
    }

    @Override
    public User deleteUser(Long id) {
        // invalid — deleteById() returns void
        return userRepository.findById(id).map(user -> {
            userRepository.deleteById(id);
            return user;
        }).orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
    }
}