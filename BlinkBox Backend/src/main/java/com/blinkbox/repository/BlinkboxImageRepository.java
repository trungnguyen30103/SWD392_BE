package com.blinkbox.repository;

import com.blinkbox.model.BlinkboxImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlinkboxImageRepository extends JpaRepository<BlinkboxImage, Long> {
    List<BlinkboxImage> findByBlinkboxBlinkboxId(Long blinkboxId);
}
