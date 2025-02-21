package com.blindbox.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer paymentId;

    @ManyToOne
    @JoinColumn(name = "orderID", nullable = false)  // Kết nối với bảng Order
    private Order order;

    @ManyToOne
    @JoinColumn(name = "userID", nullable = false)  // Kết nối với bảng User
    private User user;

    @Column(precision = 10, scale = 2)  // Định nghĩa kiểu DECIMAL(10,2)
    private BigDecimal amount;

    private String status;

    private String paymentMethod;

    private LocalDateTime createdAt;
}
