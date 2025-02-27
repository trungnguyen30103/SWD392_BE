package com.blindbox.repository;

import com.blindbox.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    Optional<Order> findById(long id);
    // Bạn có thể thêm các truy vấn tùy chỉnh nếu cần
}
