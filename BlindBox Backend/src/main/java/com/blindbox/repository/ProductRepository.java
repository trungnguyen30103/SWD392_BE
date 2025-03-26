package com.blindbox.repository;

import com.blindbox.enums.Blindbox.ProductStatus;
import com.blindbox.model.Product;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @EntityGraph(attributePaths = "productImages")
    @NonNull
    List<Product> findAll();

    @EntityGraph(attributePaths = "productImages")
    @NonNull
    Optional<Product> findById(@NonNull Integer id);

    @EntityGraph(attributePaths = "productImages")
    List<Product> findByCategory_CategoryID(@Param("categoryID") Integer categoryID);

    @Query("SELECT p FROM Product p WHERE LOWER(p.productName) LIKE LOWER(CONCAT('%', :name, '%'))")
    @EntityGraph(attributePaths = "productImages")
    List<Product> findByProductNameContainingIgnoreCase(@Param("name") String name);

    @EntityGraph(attributePaths = "productImages")
    List<Product> findByStatus(ProductStatus status);

    @EntityGraph(attributePaths = "productImages")
    List<Product> findByStatusAndCategory_CategoryID(ProductStatus status, Integer categoryID);

    // Các truy vấn đặc biệt có thể được viết tại đây nếu cần
}
