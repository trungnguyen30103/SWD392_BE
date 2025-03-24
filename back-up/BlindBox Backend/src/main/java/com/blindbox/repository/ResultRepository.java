package com.blindbox.repository;

import com.blindbox.model.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultRepository extends JpaRepository<Result, Integer> {
    // Các truy vấn đặc biệt có thể được viết tại đây nếu cần
}
