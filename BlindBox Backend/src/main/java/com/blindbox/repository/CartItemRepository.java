package com.blindbox.repository;

import com.blindbox.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer> {

    Optional<CartItem> findByCart_CartIdAndProduct_ProductID(Integer cartID, Integer productID);

    List<CartItem> findByCart_CartId(Integer cartID);

    // Các phương thức tùy chỉnh có thể được thêm vào ở đây nếu cần
}
