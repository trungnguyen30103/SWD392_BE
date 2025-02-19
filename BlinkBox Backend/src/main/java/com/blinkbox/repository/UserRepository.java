package com.blinkbox.repository;

import com.blinkbox.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // Bạn có thể thêm các phương thức tìm kiếm tùy chỉnh nếu cần
}
