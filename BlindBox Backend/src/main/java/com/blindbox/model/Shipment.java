package com.blindbox.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "shipment")
public class Shipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer shipmentID;

    @ManyToOne
    @JoinColumn(name = "orderID", referencedColumnName = "orderID", nullable = false)
    private Order order;  // Liên kết với bảng Order

    @Column(nullable = false)
    private String address;  // Địa chỉ giao hàng, không được null

    @Column(nullable = false)
    private String method;  // Phương thức giao hàng (standard, express)

    @Column(nullable = false)
    private String status;  // Trạng thái giao hàng (pending, shipped, delivered)

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();  // Thời gian tạo mặc định

    private LocalDateTime updatedAt;  // Thời gian cập nhật giao hàng

}
