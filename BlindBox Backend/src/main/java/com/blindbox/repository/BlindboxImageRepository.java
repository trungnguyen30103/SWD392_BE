package com.blindbox.repository;

import com.blindbox.model.BlindboxImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BlindboxImageRepository extends JpaRepository<BlindboxImage, Integer> {

    Optional<BlindboxImage> findByBlindbox_BlindboxIDAndBlindboxImageID(Integer blindboxID, Integer imageID);

    void deleteAllByBlindbox_BlindboxID(Integer id);
    void deleteByBlindbox_BlindboxIDAndBlindboxImageID(Integer blindboxID, Integer imageID);

    @Query("SELECT i FROM BlindboxImage i WHERE i.blindbox.blindboxID = :blindboxID")
    List<BlindboxImage> findBlindboxImagesByBlindbox_BlindboxID(@Param("blindboxID") Integer blindboxID);

    // Có thể thêm các truy vấn tùy chỉnh nếu cần
}
