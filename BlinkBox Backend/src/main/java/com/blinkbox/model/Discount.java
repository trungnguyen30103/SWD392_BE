package com.blinkbox.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "discount")
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long discountId;

    private int discountPercentage;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String status;
}
