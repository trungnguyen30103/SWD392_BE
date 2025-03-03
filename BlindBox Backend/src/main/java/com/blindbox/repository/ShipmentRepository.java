package com.blindbox.repository;

import com.blindbox.model.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, Integer> {
    // Các truy vấn đặc biệt có thể được viết tại đây nếu cần
}
