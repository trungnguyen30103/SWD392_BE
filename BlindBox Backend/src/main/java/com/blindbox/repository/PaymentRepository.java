package com.blindbox.repository;

import com.blindbox.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    // Các truy vấn đặc biệt có thể được viết tại đây nếu cần
}
