package com.blindbox.repository;

import com.blindbox.model.BlindBoxItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BlindBoxItemRepository extends JpaRepository<BlindBoxItem, Integer> {

    Optional<BlindBoxItem> findByBlindbox_BlindboxIDAndBlindboxItemID(Integer blindboxID, Integer blindboxItemID);
    void deleteAllByBlindbox_BlindboxID(Integer blindboxID);
    void deleteByBlindbox_BlindboxIDAndBlindboxItemID(Integer blindboxID, Integer blindboxItemID);

    List<BlindBoxItem> findAll();

    @Query("SELECT i FROM BlindBoxItem i WHERE i.blindbox.blindboxID = :blindboxID")
    List<BlindBoxItem> findBlindBoxItemsByBlindbox_BlindboxID(@Param("blindboxID") Integer blindboxID);
}
