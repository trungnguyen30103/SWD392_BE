package com.blindbox.repository;

import com.blindbox.enums.DiscountStatus;
import com.blindbox.model.Discount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DiscountRepository extends JpaRepository<Discount, Integer> {
    // Có thể thêm các truy vấn tuỳ chỉnh ở đây, ví dụ:
    // List<Discount> findByStatus(DiscountStatus status);

    Optional<Discount> findByStatusAndProduct_ProductID(DiscountStatus status, Integer productID);
    Optional<Discount> findByStatusAndBlindbox_BlindboxID(DiscountStatus status, Integer blindboxID);
}
