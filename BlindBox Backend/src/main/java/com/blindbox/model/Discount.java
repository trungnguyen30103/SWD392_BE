package com.blindbox.model;

import com.blindbox.enums.DiscountStatus;
import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "discount")
public class Discount {

    @Id
<<<<<<< HEAD
    private Integer discountId;

    @Column(nullable = false, length = 50)
    private String code;

    @Column(name = "percentage", nullable = false, precision = 4, scale = 2)
    private BigDecimal percentage;

    @Column(name = "valid_from", nullable = false)
    private LocalDateTime validFrom;

    @Column(name = "valid_until", nullable = false)
    private LocalDateTime validUntil;
=======
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "discount_id")
    private Integer discountId;

    @Column(nullable = false, precision = 4, scale = 2)
    private BigDecimal discountPercentage;  // Percentage of the discount

    @Column(nullable = false)
    private LocalDateTime startDate;  // Discount start date

    @Column(nullable = false)
    private LocalDateTime endDate;  // Discount end date
>>>>>>> 3ce307c8d5da7a526da900cc48537fd49c86ff5c

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DiscountStatus status;  // Enum for discount status (ACTIVE, EXPIRED)

    @ManyToOne
    @JoinColumn(name = "blindbox_id")
    private Blindbox blindbox;  // Reference to Blindbox

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;  // Reference to Product
}
