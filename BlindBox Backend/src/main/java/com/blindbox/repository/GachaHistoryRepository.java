package com.blindbox.repository;

import com.blindbox.model.GachaHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GachaHistoryRepository extends JpaRepository<GachaHistory, Integer> {
    // Bạn có thể thêm các truy vấn tuỳ chỉnh ở đây nếu cần, ví dụ:
     List<GachaHistory> findByUserId(Integer userId);
}
