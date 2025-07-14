package com.rushaul.logisitcs_backend.service.impl;
import com.rushaul.logisitcs_backend.model.Order;
import com.rushaul.logisitcs_backend.model.OrderStatus;
import com.rushaul.logisitcs_backend.repository.OrderRepository;
import com.rushaul.logisitcs_backend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceimpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order updateOrderStatus(Long orderId, String status) {
        Order existingOrder = orderRepository.findById(orderId).orElseThrow(
                () -> new RuntimeException("Order not found"));
        existingOrder.setStatus(OrderStatus.valueOf(status));
        return orderRepository.save(existingOrder);
    }

    @Override
    public Order deleteOrder(Long orderId) {
        return orderRepository.findById(orderId).map(order -> {
            orderRepository.deleteById(orderId);
            return order;
        }).orElseThrow(() -> new RuntimeException("Order not found with ID: " + orderId));
    }


    @Override
    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + orderId));
    }


    @Override
    public List<Order> getOrdersByCustomer(Long id) {
        return orderRepository.findByCustomerId(id);
    }

}