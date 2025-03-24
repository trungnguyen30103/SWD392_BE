package com.blindbox.controller;

import com.blindbox.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user-account")
public class UserAccountController {

    @Autowired
    private UserAccountService userAccountService;

    // API để tạo tài khoản người dùng với initialBalance kiểu double
    @PostMapping("/create")
    public String createUserAccount(@RequestParam Integer userId, @RequestParam double initialBalance) {
        userAccountService.createUserAccount(userId, initialBalance);
        return "User account created successfully!";
    }
}
