package com.blindbox.controller;

import com.blindbox.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/user-account")
public class UserAccountController {

    @Autowired
    private UserAccountService userAccountService;


    @PostMapping("/create")
    public String createUserAccount(@RequestParam Integer userId, @RequestParam BigDecimal initialBalance) {
        userAccountService.createUserAccount(userId, initialBalance);
        return "User account created successfully!";
    }
}
