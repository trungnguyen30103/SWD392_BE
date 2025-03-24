package com.blindbox.service;

import com.blindbox.model.OrderDetail;
import java.util.List;

public interface OrderDetailService {
    List<OrderDetail> getAllOrderDetails();
    OrderDetail getOrderDetailById(Integer id);
    OrderDetail createOrderDetail(OrderDetail orderDetail);
    OrderDetail updateOrderDetail(Integer id, OrderDetail orderDetail);
    boolean deleteOrderDetail(Integer id);
}
