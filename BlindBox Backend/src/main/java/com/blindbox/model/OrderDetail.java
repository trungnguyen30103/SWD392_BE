package com.blindbox.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "orderdetail")
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderDetailID;

    @ManyToOne
    @JoinColumn(name = "orderID", nullable = false)
    private Order order;  // Thay vì Integer orderID

    @ManyToOne
    @JoinColumn(name = "blindboxID", nullable = false)
    private Blindbox blindbox;  // Thay vì Integer blindboxID

    @ManyToOne
    @JoinColumn(name = "productID", nullable = false)
    private Product product;  // Thay vì Integer productID

    private Integer quantity;

    @Column(precision = 10, scale = 2)  // Định nghĩa kiểu DECIMAL(10,2)
    private BigDecimal price;
}
