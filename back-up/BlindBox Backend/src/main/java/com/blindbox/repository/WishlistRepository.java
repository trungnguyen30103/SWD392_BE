package com.blindbox.repository;

import com.blindbox.model.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Integer> {
    // Các phương thức bổ sung có thể thêm vào ở đây nếu cần
}
