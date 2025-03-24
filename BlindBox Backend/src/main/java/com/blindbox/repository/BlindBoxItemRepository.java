package com.blindbox.repository;

import com.blindbox.model.BlindBoxItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BlindBoxItemRepository extends JpaRepository<BlindBoxItem, Integer> {

    Optional<BlindBoxItem> findByBlindbox_BlindboxIDAndAndBlindboxItemID(Integer blindboxID, Integer blindboxItemID);
    void deleteAllByBlindbox_BlindboxID(Integer blindboxID);
    void deleteByBlindbox_BlindboxIDAndBlindboxItemID(Integer blindboxID, Integer blindboxItemID);

    List<BlindBoxItem> findAll();
}
