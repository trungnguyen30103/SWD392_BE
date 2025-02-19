package com.blinkbox.service.impl;

import com.blinkbox.model.User;
import com.blinkbox.repository.UserRepository;
import com.blinkbox.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long userID) {
        Optional<User> user = userRepository.findById(userID);
        return user.orElse(null);
    }

    @Override
    public User updateUser(Long userID, User user) {
        if (userRepository.existsById(userID)) {
            user.setUserID(userID);
            return userRepository.save(user);
        }
        return null;
    }

    @Override
    public void deleteUser(Long userID) {
        userRepository.deleteById(userID);
    }
}
