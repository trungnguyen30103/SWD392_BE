package com.blinkbox.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;


@Data
@Entity
@Table(name = "blinkboxes")
public class Blinkbox {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long blinkboxID;

    private String blinkboxName;
    private String description;
    private Double price;
    private Long categoryID;
    private Double rating;
    private Integer stock;
    private LocalDateTime createdAt;
    private LocalDateTime lastUpdated;
}