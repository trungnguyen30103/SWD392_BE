package com.blindbox.repository;

import com.blindbox.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    // Đảm bảo phương thức trả về Optional<User>
    Optional<User> findByUserName(String username);
}
