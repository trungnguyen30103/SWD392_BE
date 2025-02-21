package com.blindbox.repository;

import com.blindbox.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    // Bạn có thể thêm các phương thức tìm kiếm khác tại đây
    User findByUserName(String userName); // Ví dụ tìm kiếm theo tên người dùng
}
