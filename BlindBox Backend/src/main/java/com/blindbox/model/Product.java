package com.blindbox.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productID;

    private String productName;

    private String description;

    @ManyToOne
    @JoinColumn(name = "categoryID", nullable = false)  // Quan hệ với bảng Category
    private Category category;  // Liên kết với bảng Category

    @Column(precision = 10, scale = 2)  // Định nghĩa kiểu DECIMAL(10,2)
    private BigDecimal price;

    private Integer stock;

    private LocalDateTime createdAt;

    private LocalDateTime lastUpdated;
}
