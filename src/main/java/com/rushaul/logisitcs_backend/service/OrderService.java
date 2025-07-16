package com.rushaul.logisitcs_backend.service;

import com.rushaul.logisitcs_backend.dto.OrderRequestDTO;
import com.rushaul.logisitcs_backend.model.Order;
import java.util.List;

public interface OrderService {
    Order createOrder(OrderRequestDTO dto);
    List<Order> getAllOrders();
    Order updateOrderStatus(Long orderId, String status);
    Order deleteOrder(Long orderId);
    Order getOrderById(Long orderId);
    List<Order> getOrdersByCustomer(Long id);
    boolean verifyPickupOtp(Long orderId, String otp);
    boolean verifyDeliveryOtp(Long orderId, String otp);
}