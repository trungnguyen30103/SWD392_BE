package com.blindbox.service;

import com.blindbox.model.Order;
import java.util.List;

public interface OrderService {

    // Tạo đơn hàng mới
    Order createOrder(Order order);

    // Lấy tất cả các đơn hàng
    List<Order> getAllOrders();

    // Lấy đơn hàng theo ID
    Order getOrderById(Integer orderId);

    // Cập nhật thông tin đơn hàng
    Order updateOrder(Integer orderId, Order order);

    // Xóa đơn hàng theo ID
    boolean deleteOrder(Integer orderId);

    // Quy's
    Order createOrderFromCart(Integer userId);

    boolean payForOrderByUserBalance(Integer userId, Integer orderId);
}
