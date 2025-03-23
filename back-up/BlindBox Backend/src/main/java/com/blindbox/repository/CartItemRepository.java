package com.blindbox.repository;

import com.blindbox.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
    // Các phương thức tùy chỉnh có thể được thêm vào ở đây nếu cần
}
