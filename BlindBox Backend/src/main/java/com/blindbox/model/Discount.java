package com.blindbox.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "discount")
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer discountId;

    @Column(nullable = false, precision = 5, scale = 2)  // Không cho phép NULL
    private BigDecimal discountPercentage;

    @Column(nullable = false)
    private LocalDateTime startDate;

    @Column(nullable = false)
    private LocalDateTime endDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DiscountStatus status;

    // Nếu giảm giá áp dụng cho một sản phẩm cụ thể
    @ManyToOne
    @JoinColumn(name = "blindboxID", referencedColumnName = "blindboxID")
    private Blindbox blindbox;
}
