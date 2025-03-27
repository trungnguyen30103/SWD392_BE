package com.blindbox.repository;

import com.blindbox.enums.Blindbox.BlindboxItemStatus;
import com.blindbox.model.BlindBoxItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface BlindBoxItemRepository extends JpaRepository<BlindBoxItem, Integer> {

    Optional<BlindBoxItem> findByBlindbox_BlindboxIDAndBlindboxItemID(Integer blindboxID, Integer blindboxItemID);

    List<BlindBoxItem> findAllByBlindbox_BlindboxID(Integer blindboxID);

    @NonNull
    List<BlindBoxItem> findAll();

    List<BlindBoxItem> findByStatus(BlindboxItemStatus status);

}
