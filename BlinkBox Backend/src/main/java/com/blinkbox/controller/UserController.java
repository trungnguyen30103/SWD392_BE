package com.blinkbox.controller;

import com.blinkbox.model.User;
import com.blinkbox.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{userID}")
    public User getUserById(@PathVariable Long userID) {
        return userService.getUserById(userID);
    }

    @PutMapping("/{userID}")
    public User updateUser(@PathVariable Long userID, @RequestBody User user) {
        return userService.updateUser(userID, user);
    }

    @DeleteMapping("/{userID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long userID) {
        userService.deleteUser(userID);
    }
}
