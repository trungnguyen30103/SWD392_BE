package com.blindbox.repository;

import com.blindbox.model.Discount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountRepository extends JpaRepository<Discount, Integer> {
    // Có thể thêm các truy vấn tuỳ chỉnh ở đây, ví dụ:
    // List<Discount> findByStatus(DiscountStatus status);
}
