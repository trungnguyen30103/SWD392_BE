package com.blindbox.repository;

import com.blindbox.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    // Bạn có thể thêm các truy vấn tùy chỉnh nếu cần
}
