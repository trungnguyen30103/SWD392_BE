package com.blinkbox.repository;

import com.blinkbox.model.Blinkbox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlinkboxRepository extends JpaRepository<Blinkbox, Long> {
}
