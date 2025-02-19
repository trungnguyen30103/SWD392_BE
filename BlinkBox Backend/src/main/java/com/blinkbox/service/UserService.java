package com.blinkbox.service;

import com.blinkbox.model.User;
import java.util.List;

public interface UserService {
    User createUser(User user);
    List<User> getAllUsers();
    User getUserById(Long userID);
    User updateUser(Long userID, User user);
    void deleteUser(Long userID);
}
