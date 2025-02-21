package com.blindbox.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "productimage")
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productImageId;

    @ManyToOne
    @JoinColumn(name = "productID", nullable = false)
    private Product product;  // Khóa ngoại liên kết với bảng Product

    private String imageUrl;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
