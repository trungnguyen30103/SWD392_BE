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
<<<<<<< HEAD
=======
    @GeneratedValue(strategy = GenerationType.IDENTITY)
>>>>>>> 3ce307c8d5da7a526da900cc48537fd49c86ff5c
    @Column(name = "payment_id")
    private Integer paymentID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "amount", precision = 10, scale = 2, nullable = false)
    private BigDecimal amount;

    @Column(name = "status", nullable = false, length = 50)
    private String status;

    @Column(name = "method", nullable = false, length = 50)
    private String paymentMethod;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "transaction_id", nullable = false, length = 100, unique = true)
    private String transactionId;
}
