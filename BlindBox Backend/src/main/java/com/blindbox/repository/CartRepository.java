package com.blindbox.repository;

import com.blindbox.model.Cart;
import com.blindbox.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

    Optional<Cart> findByUser_UserID(Integer userID);

    // Các phương thức tùy chỉnh có thể được thêm vào ở đây nếu cần
}
