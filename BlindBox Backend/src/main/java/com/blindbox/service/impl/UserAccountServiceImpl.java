package com.blindbox.service.impl;

import com.blindbox.model.UserAccount;
import com.blindbox.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAccountServiceImpl {

    @Autowired
    private UserAccountRepository userAccountRepository;

    // Ví dụ phương thức cập nhật số dư tài khoản
    public void updateBalance(Integer userId, double amount) {
        UserAccount userAccount = userAccountRepository.findByUserUserID(userId);
        if (userAccount != null) {
            userAccount.setBalance(userAccount.getBalance() + amount);  // Sử dụng kiểu double
            userAccountRepository.save(userAccount);
        } else {
            throw new RuntimeException("User account not found.");
        }
    }

}
