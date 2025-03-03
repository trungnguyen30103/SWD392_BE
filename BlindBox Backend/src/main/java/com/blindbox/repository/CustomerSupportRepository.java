package com.blindbox.repository;

import com.blindbox.model.CustomerSupport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerSupportRepository extends JpaRepository<CustomerSupport, Integer> {
    // Bạn có thể thêm các phương thức truy vấn tùy chỉnh tại đây nếu cần
}
