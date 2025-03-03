package com.blindbox.repository;

import com.blindbox.model.BlindboxImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlindboxImageRepository extends JpaRepository<BlindboxImage, Integer> {
    // Có thể thêm các truy vấn tùy chỉnh nếu cần
}
