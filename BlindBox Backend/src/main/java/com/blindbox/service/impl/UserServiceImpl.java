package com.blindbox.service.impl;

import com.blindbox.model.User;
import com.blindbox.repository.UserRepository;
import com.blindbox.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }

    @Override
    public User createUser(User user) {
        // Check if email already exists
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("User with email " +user.getEmail() + " already exists");
        }

        // Ensure required fields are provided or set default values
        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            throw new IllegalArgumentException("Password is required.");
        }
        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("Email is required.");
        }
        if (user.getAddress() == null || user.getAddress().isEmpty()) {
            throw new IllegalArgumentException("Address is required.");
        }
        if (user.getUserName() == null || user.getUserName().trim().isEmpty()) {
            throw new IllegalArgumentException("Username is required.");
        }
        if (user.getPhone() == null || user.getPhone().trim().isEmpty()) {
            throw new IllegalArgumentException("Phone number is required.");
        }
        if (user.getFirstName() == null || user.getFirstName().isEmpty()) {
            throw new IllegalArgumentException("First name is required.");
        }
        if (user.getLastName() == null || user.getLastName().isEmpty()) {
            throw new IllegalArgumentException("Last name is required.");
        }

        // Set default values if missing
        if (user.getRole() == null) {
            user.setRole("CUSTOMER"); // Default role
        }
        if (user.getStatus() == null) {
            user.setStatus("ACTIVE"); // Default status
        }

        // Set timestamps
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());


        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long id, User userUpdates) {
        // Find existing user by ID
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        // Update only provided fields (keep existing values if not provided)
        if (userUpdates.getUserName() != null && !userUpdates.getUserName().trim().isEmpty()) {
            existingUser.setUserName(userUpdates.getUserName());
        }
        if (userUpdates.getEmail() != null && !userUpdates.getEmail().trim().isEmpty()) {
            if (!existingUser.getEmail().equals(userUpdates.getEmail()) && userRepository.findByEmail(userUpdates.getEmail()).isPresent()) {
                throw new RuntimeException("Email is already in use!");
            }
            existingUser.setEmail(userUpdates.getEmail());
        }
        if (userUpdates.getPassword() != null && !userUpdates.getPassword().trim().isEmpty()) {
            existingUser.setPassword(userUpdates.getPassword());
        }
        if (userUpdates.getPhone() != null) {
            existingUser.setPhone(userUpdates.getPhone());
        }
        if (userUpdates.getAddress() != null) {
            existingUser.setAddress(userUpdates.getAddress());
        }
        if (userUpdates.getRole() != null) {
            existingUser.setRole(userUpdates.getRole());
        }
        if (userUpdates.getFirstName() != null) {
            existingUser.setFirstName(userUpdates.getFirstName());
        }
        if (userUpdates.getLastName() != null) {
            existingUser.setLastName(userUpdates.getLastName());
        }
        if (userUpdates.getStatus() != null) {
            existingUser.setStatus(userUpdates.getStatus());
        }

        // Update timestamp
        existingUser.setUpdatedAt(LocalDateTime.now());

        // Save updated user to database
        return userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
