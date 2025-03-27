package com.blindbox.repository;

import com.blindbox.enums.PaymentStatus;
import com.blindbox.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    Optional<Order> findById(Integer orderId);

    Optional<Order> findByUser_UserIDAndPaymentStatus(Integer userID, PaymentStatus status);
}
