package com.blindbox.repository;

import com.blindbox.enums.Blindbox.BlindboxStatus;
import com.blindbox.model.Blindbox;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BlindboxRepository extends JpaRepository<Blindbox, Integer> {

    @EntityGraph(attributePaths = {"blindboxImages", "blindBoxItems"})
    @NonNull
    List<Blindbox> findAll();

    @EntityGraph(attributePaths = {"blindboxImages", "blindBoxItems"})
    @NonNull
    Optional<Blindbox> findById(@NonNull Integer id);

    @EntityGraph(attributePaths = {"blindboxImages", "blindBoxItems"})
    List<Blindbox> findByCategory_CategoryID(@Param("categoryID") Integer categoryID);

    @Query("SELECT b FROM Blindbox b WHERE LOWER(b.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    @EntityGraph(attributePaths = {"blindboxImages", "blindBoxItems"})
    List<Blindbox> findByBlindboxNameContainingIgnoreCase(@Param("name") String name);

    @EntityGraph(attributePaths = {"blindboxImages", "blindBoxItems"})
    List<Blindbox> findByStatus(BlindboxStatus status);

    @EntityGraph(attributePaths = {"blindboxImages", "blindBoxItems"})
    List<Blindbox> findByStatusAndCategory_CategoryID(BlindboxStatus status, Integer categoryID);

    // Có thể thêm các truy vấn đặc biệt nếu cần
}
