package com.blindbox.repository;

import com.blindbox.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
    // Các phương thức tùy chỉnh có thể được thêm vào ở đây nếu cần
}
