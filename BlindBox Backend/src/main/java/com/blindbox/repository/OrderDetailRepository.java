package com.blindbox.repository;

import com.blindbox.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
    // Các truy vấn đặc biệt có thể được viết tại đây nếu cần
}
