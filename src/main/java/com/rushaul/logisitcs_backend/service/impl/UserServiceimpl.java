package com.rushaul.logisitcs_backend.service.impl;

import com.rushaul.logisitcs_backend.model.User;
import com.rushaul.logisitcs_backend.repository.UserRepository;
import com.rushaul.logisitcs_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceimpl implements UserService, UserDetailsService {

    // -------------------------------------------------- DEPENDENCIES
    private final UserRepository userRepository;

    // -------------------------------------------------- CONSTRUCTOR
    @Autowired
    public UserServiceimpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    // -------------------------------------------------- FIND USER BY USERNAME
    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }


    // -------------------------------------------------- CREATE USER
    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }


    // -------------------------------------------------- GET USER BY ID
    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }


    // -------------------------------------------------- GET ALL USERS
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    // -------------------------------------------------- UPDATE USER
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


    // -------------------------------------------------- DELETE USER
    @Override
    public User deleteUser(Long id) {
        return userRepository.findById(id).map(user -> {
            userRepository.deleteById(id);
            return user;
        }).orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
    }


    // ----------------------------- USERDETAILS SERVICE IMPLEMENTATION
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getRole()))
        );
    }
}
