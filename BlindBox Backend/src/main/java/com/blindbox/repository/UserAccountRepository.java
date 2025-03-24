package com.blindbox.repository;

import com.blindbox.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepository extends JpaRepository<UserAccount, Integer> {

    UserAccount findByUserUserID(Integer userId);
}
