package com.blindbox.repository;

import com.blindbox.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    // Đảm bảo phương thức trả về Optional<User>
    Optional<User> findByUserName(String username);

    Optional<User> findByUserIDAndRole_RoleID(Integer userID, Integer roleID);

    List<User> findByUserNameContainingIgnoreCase(String userName);

    List<User> findByFullNameContainingIgnoreCase(String fullName);

    List<User> findByPhoneContainingIgnoreCase(String phone);

    List<User> findByEmailContainingIgnoreCase(String email);

    List<User> findByRole_RoleID(Integer roleID);

}
