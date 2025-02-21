package com.blindbox.model;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;


@Data
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productID;
    private String productName;
    private String description;
    private Long categoryID;
    private Double price;
    private Integer stock;
    private LocalDateTime createdAt;
    private LocalDateTime lastUpdated;
}