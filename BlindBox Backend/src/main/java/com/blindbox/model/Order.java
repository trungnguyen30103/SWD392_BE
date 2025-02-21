package com.blindbox.model;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderID;
    private Long userID;
    private Double totalAmount;
    private String status;
    private Long shipmentID;
    private String gachaType;
    private LocalDateTime createdAt;
    private Long discountID;
}
