package com.blindbox.model;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "order_details")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderDetailID;
    private Long orderID;
    private Long blindboxID;
    private Long productID;
    private Integer quantity;
    private Double price;
}