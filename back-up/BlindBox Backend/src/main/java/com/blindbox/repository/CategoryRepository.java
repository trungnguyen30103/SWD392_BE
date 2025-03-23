package com.blindbox.repository;

import com.blindbox.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    // Bạn có thể thêm các phương thức truy vấn tùy chỉnh ở đây nếu cần
}
