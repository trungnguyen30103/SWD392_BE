package com.blindbox.repository;

import com.blindbox.model.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultRepository extends JpaRepository<Result, Integer> {
    List<Result> findByOrder_OrderId(Integer orderId);
    List<Result> findByBlindboxItem_Blindbox_BlindboxID(Integer blindboxId);

    // Các truy vấn đặc biệt có thể được viết tại đây nếu cần
}
