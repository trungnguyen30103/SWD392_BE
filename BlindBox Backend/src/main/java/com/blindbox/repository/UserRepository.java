package com.blindbox.repository;

import com.blindbox.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    // Bạn có thể thêm các phương thức tìm kiếm khác tại đây
    Optional<User> findByUserName(String userName); // Ví dụ tìm kiếm theo tên người dùng
    Optional<User> findById(long id);
}
