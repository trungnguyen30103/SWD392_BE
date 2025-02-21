package com.blindbox.service;

import com.blindbox.model.OrderDetail;
import com.blindbox.repository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    // Tạo chi tiết đơn hàng mới
    @Override
    public OrderDetail createOrderDetail(OrderDetail orderDetail) {
        return orderDetailRepository.save(orderDetail);
    }

    // Lấy tất cả chi tiết đơn hàng
    @Override
    public List<OrderDetail> getAllOrderDetails() {
        return orderDetailRepository.findAll();
    }

    // Lấy chi tiết đơn hàng theo ID
    @Override
    public Optional<OrderDetail> getOrderDetailById(Long orderDetailID) {
        return orderDetailRepository.findById(orderDetailID);
    }

    // Xóa chi tiết đơn hàng theo ID
    @Override
    public void deleteOrderDetail(Long orderDetailID) {
        orderDetailRepository.deleteById(orderDetailID);
    }
}
