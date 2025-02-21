package com.blindbox.service;

import com.blindbox.model.OrderDetail;

import java.util.List;
import java.util.Optional;

public interface OrderDetailService {

    // Tạo chi tiết đơn hàng mới
    OrderDetail createOrderDetail(OrderDetail orderDetail);

    // Lấy tất cả chi tiết đơn hàng
    List<OrderDetail> getAllOrderDetails();

    // Lấy chi tiết đơn hàng theo ID
    Optional<OrderDetail> getOrderDetailById(Long orderDetailID);

    // Xóa chi tiết đơn hàng theo ID
    void deleteOrderDetail(Long orderDetailID);
}
