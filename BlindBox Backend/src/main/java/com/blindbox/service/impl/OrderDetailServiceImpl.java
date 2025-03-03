package com.blindbox.service.impl;

import com.blindbox.model.OrderDetail;
import com.blindbox.repository.OrderDetailRepository;
import com.blindbox.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Override
    public List<OrderDetail> getAllOrderDetails() {
        return orderDetailRepository.findAll();
    }

    @Override
    public OrderDetail getOrderDetailById(Integer id) {
        Optional<OrderDetail> orderDetail = orderDetailRepository.findById(id);
        return orderDetail.orElse(null);
    }

    @Override
    public OrderDetail createOrderDetail(OrderDetail orderDetail) {
        return orderDetailRepository.save(orderDetail);
    }

    @Override
    public OrderDetail updateOrderDetail(Integer id, OrderDetail orderDetail) {
        if (orderDetailRepository.existsById(id)) {
            orderDetail.setOrderDetailID(id);
            return orderDetailRepository.save(orderDetail);
        }
        return null;
    }

    @Override
    public boolean deleteOrderDetail(Integer id) {
        if (orderDetailRepository.existsById(id)) {
            orderDetailRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
