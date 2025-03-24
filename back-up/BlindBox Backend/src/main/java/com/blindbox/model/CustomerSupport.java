package com.blindbox.model;

import com.blindbox.enums.SupportStatus;
import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "customer_support") // ✅ Sử dụng snake_case cho MySQL
public class CustomerSupport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "support_id")
    private Integer supportID; // ✅ Đổi thành camelCase

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    @JsonIgnore // ✅ Tránh vòng lặp vô hạn trong JSON
    private User user;

    @ManyToOne
    @JoinColumn(name = "order_id",nullable = false)
    @JsonIgnore // ✅ Tránh vòng lặp vô hạn trong JSON
    private Order order;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String message;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SupportStatus status;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now(); // ✅ Tự động gán thời gian tạo
    }
}
