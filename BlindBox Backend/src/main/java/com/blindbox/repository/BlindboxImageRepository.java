package com.blindbox.repository;

import com.blindbox.model.BlindboxImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlindboxImageRepository extends JpaRepository<BlindboxImage, Integer> {
    List<BlindboxImage> findByBlindbox_BlindboxID(Integer blindboxId);
}
