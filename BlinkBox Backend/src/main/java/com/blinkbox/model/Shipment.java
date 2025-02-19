package com.blinkbox.model;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;


@Data
@Entity
@Table(name = "shipments")
public class Shipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shipmentID;
    private Long orderID;
    private String trackingNumber;
    private String carrier;
    private String status;
    private LocalDateTime shippedAt;
}
