package com.blindbox.repository;

import com.blindbox.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
    // Bạn có thể thêm các truy vấn tùy chỉnh nếu cần
}
