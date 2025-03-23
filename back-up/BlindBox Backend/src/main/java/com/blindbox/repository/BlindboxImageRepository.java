package com.blindbox.repository;

import com.blindbox.model.BlindboxImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BlindboxImageRepository extends JpaRepository<BlindboxImage, Integer> {

    Optional<BlindboxImage> findByBlindbox_BlindboxIDAndBlindboxImageID(Integer blindboxID, Integer imageID);

    void deleteAllByBlindbox_BlindboxID(Integer id);
    void deleteByBlindbox_BlindboxIDAndBlindboxImageID(Integer blindboxID, Integer imageID);

    // Có thể thêm các truy vấn tùy chỉnh nếu cần
}
