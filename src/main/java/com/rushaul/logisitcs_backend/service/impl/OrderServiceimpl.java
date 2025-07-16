package com.rushaul.logisitcs_backend.service.impl;
import com.rushaul.logisitcs_backend.dto.OrderRequestDTO;
import com.rushaul.logisitcs_backend.model.Hub;
import com.rushaul.logisitcs_backend.model.Order;
import com.rushaul.logisitcs_backend.model.OrderStatus;
import com.rushaul.logisitcs_backend.model.User;
import com.rushaul.logisitcs_backend.repository.HubRepository;
import com.rushaul.logisitcs_backend.repository.OrderRepository;
import com.rushaul.logisitcs_backend.repository.UserRepository;
import com.rushaul.logisitcs_backend.service.OrderService;
import com.rushaul.logisitcs_backend.utility.OtpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import static com.rushaul.logisitcs_backend.utility.OtpUtil.generateOtp;

@Service
public class OrderServiceimpl implements OrderService {

    @Autowired
    private OtpUtil otpUtil;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private HubRepository hubRepository;

    @Override
    public Order createOrder(OrderRequestDTO dto) {
        Order order = new Order();

        order.setExternalOrderId(dto.getExternalOrderId());
        order.setSellerName(dto.getSellerName());
        order.setSellerAddress(dto.getSellerAddress());
        order.setDestinationAddress(dto.getDestinationAddress());

        order.setStatus(OrderStatus.PLACED);
        order.setPlacedAt(Timestamp.valueOf(LocalDateTime.now()));
        order.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));

        // Map ID to entities
        User customer = userRepository.findById(dto.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Hub pickupHub = hubRepository.findById(dto.getPickupHubId())
                .orElseThrow(() -> new RuntimeException("Pickup hub not found"));

        Hub destinationHub = hubRepository.findById(dto.getDestinationHubId())
                .orElseThrow(() -> new RuntimeException("Destination hub not found"));

        order.setCustomer(customer);
        order.setPickupHub(pickupHub);
        order.setDestinationHub(destinationHub);

        // Use OtpUtil
        order.setOtpPickup(generateOtp());
        order.setOtpDelivery(generateOtp());

        return orderRepository.save(order);
    }


    @Override
    public boolean verifyPickupOtp(Long orderId, String otp) {
        Order order = orderRepository.findById(orderId).orElseThrow();
        if (order.getOtpPickup().equals(otp)) {
            order.setStatus(OrderStatus.SHIPPED);
            orderRepository.save(order);
            return true;
        }
        return false;
    }

    @Override
    public boolean verifyDeliveryOtp(Long orderId, String otp) {
        Order order = orderRepository.findById(orderId).orElseThrow();
        if (order.getOtpDelivery().equals(otp)) {
            order.setStatus(OrderStatus.DELIVERED);
            orderRepository.save(order);
            return true;
        }
        return false;
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