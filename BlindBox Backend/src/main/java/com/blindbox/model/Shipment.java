package com.blindbox.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "shipment")
public class Shipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer shipmentID;

    @ManyToOne
    @JoinColumn(name = "orderID", referencedColumnName = "orderID", nullable = false)
    private Order order;

    private String trackingNumber;
    private String carrier;

    @Enumerated(EnumType.STRING)
    private ShipmentStatus status;


    private LocalDateTime shippedAt;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
