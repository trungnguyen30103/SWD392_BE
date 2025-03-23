package com.blindbox.service.impl;

import com.blindbox.model.User;
import com.blindbox.model.UserAccount;
import com.blindbox.repository.UserAccountRepository;
import com.blindbox.repository.UserRepository;
import com.blindbox.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UserAccountServiceImpl implements UserAccountService {

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private UserRepository userRepository;


    @Override
    public void createUserAccount(Integer userId, BigDecimal initialBalance) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        UserAccount userAccount = new UserAccount();
        userAccount.setUser(user);
        userAccount.setBalance(initialBalance);

        userAccountRepository.save(userAccount);
    }
}
