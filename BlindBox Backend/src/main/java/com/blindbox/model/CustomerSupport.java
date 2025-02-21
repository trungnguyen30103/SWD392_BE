package com.blindbox.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "customersupport")
public class CustomerSupport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer supportID;

    @ManyToOne
    @JoinColumn(name = "userID", referencedColumnName = "userID", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "orderID", referencedColumnName = "orderID", nullable = false)
    private Order order;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String message;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SupportStatus status;

    @Column(nullable = false)
    private LocalDateTime createdAt;
}
