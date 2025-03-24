package com.blindbox.model;

import com.blindbox.enums.DiscountStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "discount")
public class Discount {

    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "discount_id")
    private Integer discountId;

    @Column(name = "percentage", nullable = false)
    private Double discountPercentage;  // Percentage of the discount

    @Column(nullable = false)
    private LocalDateTime valid_from;  // Discount start date

    @Column(nullable = false)
    private LocalDateTime valid_until;  // Discount end date

    @Column(nullable = false)
    private LocalDateTime created_at;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DiscountStatus status;  // Enum for discount status (ACTIVE, EXPIRED, PENDING)

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "blindbox_id")
    private Blindbox blindbox;  // Reference to Blindbox

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;  // Reference to Product
}
