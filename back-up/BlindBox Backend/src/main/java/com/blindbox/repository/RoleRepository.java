package com.blindbox.repository;

import com.blindbox.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    // Các truy vấn đặc biệt có thể được viết tại đây nếu cần
}
