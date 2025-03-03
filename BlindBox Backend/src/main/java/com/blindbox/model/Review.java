package com.blindbox.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Integer reviewID;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private User user;  // Liên kết với bảng User

    @ManyToOne
    @JoinColumn(name = "blindbox_id", referencedColumnName = "blindbox_id", nullable = false)
    private Blindbox blindbox;  // Liên kết với bảng Blindbox

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id", nullable = false)
    private Product product;  // Liên kết với bảng Product

    @Column(name = "rating")
    private Integer rating;  // Đánh giá của người dùng (1 đến 5)

    @Column(name = "review_text", columnDefinition = "TEXT")
    private String reviewText;  // Nội dung đánh giá, có thể null

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();  // Thời gian tạo đánh giá, mặc định là thời gian hiện tại

    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    private LocalDateTime updatedAt = LocalDateTime.now();  // Thời gian cập nhật đánh giá

}
