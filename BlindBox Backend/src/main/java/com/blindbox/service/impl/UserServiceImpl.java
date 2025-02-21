package com.blindbox.service.impl;

import com.blindbox.model.User;
import com.blindbox.repository.UserRepository;
import com.blindbox.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public User getUserById(Integer id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null); // Trả về null nếu không tìm thấy người dùng
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Integer id, User user) {
        if (userRepository.existsById(id)) {
            user.setUserID(id);
            return userRepository.save(user);
        }
        return null; // Trả về null nếu không tìm thấy người dùng
    }

    @Override
    public boolean deleteUser(Integer id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false; // Trả về false nếu không tìm thấy người dùng
    }
}
