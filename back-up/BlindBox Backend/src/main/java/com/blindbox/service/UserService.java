package com.blindbox.service;

import com.blindbox.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(Integer id);
    User createUser(User user);
    User updateUser(Integer id, User user);
    boolean deleteUser(Integer id);
}
