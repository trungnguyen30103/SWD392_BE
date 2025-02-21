package com.blindbox.repository;

import com.blindbox.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    // Thêm các phương thức truy vấn tùy chỉnh nếu cần
}
