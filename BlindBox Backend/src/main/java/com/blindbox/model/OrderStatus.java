package com.blindbox.model;

public enum OrderStatus {
    PENDING,    // Đang chờ xử lý
    CONFIRMED,  // Đã xác nhận
    SHIPPED,    // Đang giao hàng
    DELIVERED,  // Đã giao hàng
    CANCELED    // Đã hủy
}
