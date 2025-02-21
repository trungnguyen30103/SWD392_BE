package com.blindbox.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderID;

    @ManyToOne
    @JoinColumn(name = "userID", nullable = false)
    private User user;  // Thay vì dùng Integer userID

    @Column(precision = 10, scale = 2)
    private BigDecimal totalAmount;

    @Enumerated(EnumType.STRING)  // Chuyển status thành Enum để tránh lỗi dữ liệu
    private OrderStatus status;

    @ManyToOne
    @JoinColumn(name = "shipmentID")
    private Shipment shipment;  // Thay vì dùng Integer shipmentID

    private String gachaType;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();  // Gán thời gian mặc định khi tạo đơn hàng

    @ManyToOne
    @JoinColumn(name = "discountID")
    private Discount discount;  // Thay vì dùng Integer discountID
}
