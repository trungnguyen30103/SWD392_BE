package com.blindbox.repository;

import com.blindbox.model.BlindBoxItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlindBoxItemRepository extends JpaRepository<BlindBoxItem, Integer> {

    List<BlindBoxItem> findAll();
}
