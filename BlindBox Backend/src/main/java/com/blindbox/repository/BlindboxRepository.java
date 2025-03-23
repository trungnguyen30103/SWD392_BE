package com.blindbox.repository;

import com.blindbox.model.Blindbox;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BlindboxRepository extends JpaRepository<Blindbox, Integer> {

    @EntityGraph(attributePaths = "blindboxImages")
    @NonNull
    List<Blindbox> findAll();

    @EntityGraph(attributePaths = "blindboxImages")
    @NonNull
    Optional<Blindbox> findById(@NonNull Integer id);

    @EntityGraph(attributePaths = "blindboxImages")
    List<Blindbox> findByBlindboxNameRegexIgnoreCase(String regex);

    // Có thể thêm các truy vấn đặc biệt nếu cần
}
