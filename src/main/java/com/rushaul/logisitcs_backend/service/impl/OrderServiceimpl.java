package com.rushaul.logisitcs_backend.service.impl;
import com.rushaul.logisitcs_backend.dto.OrderRequestDTO;
import com.rushaul.logisitcs_backend.model.*;
import com.rushaul.logisitcs_backend.repository.DeliveryAssignmentRepository;
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
    @Autowired
    private DeliveryAssignmentRepository deliveryAssignmentRepository;

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
    public Order verifyPickupOtp(Long orderId, String otp) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not Found"));
        if (!order.getOtpPickup().equals(otp)) {
            throw new RuntimeException(("Invalid Pickup OTP"));
        }
        order.setStatus(OrderStatus.SHIPPED);
        order.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));

        DeliveryAssignment assignment = deliveryAssignmentRepository
                .findByOrder(order)
                .orElseThrow(() -> new RuntimeException("Assignment Not Found"));
        assignment.setStatus(AssignmentStatus.valueOf("CONFIRMED"));
        assignment.setConfirmedAt(Timestamp.valueOf(LocalDateTime.now()));
        deliveryAssignmentRepository.save(assignment);
        return orderRepository.save(order);
    }

    @Override
    public Order verifyDeliveryOtp(Long orderId, String otp) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if (!order.getOtpDelivery().equals(otp)) {
            throw new RuntimeException("Invalid Delivery OTP");
        }
        if (order.getStatus() != OrderStatus.OUT_FOR_DELIVERY) {
            throw new RuntimeException("Order is not ready for delivery");
        }
        order.setStatus(OrderStatus.DELIVERED);
        order.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));

        DeliveryAssignment assignment = deliveryAssignmentRepository
                .findByOrder(order)
                .orElseThrow(() -> new RuntimeException("Delivery assignment not found"));

        assignment.setStatus(AssignmentStatus.COMPLETED);
        assignment.setCompletedAt(Timestamp.valueOf(LocalDateTime.now()));
        deliveryAssignmentRepository.save(assignment);

        return orderRepository.save(order);
    }

    @Override
    public Order markOrderAsShipped(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if (order.getStatus() != OrderStatus.PICKUP) {
            throw new RuntimeException("Order must be in PICKUP status to mark as SHIPPED");
        }

        order.setStatus(OrderStatus.SHIPPED);
        order.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));

        return orderRepository.save(order);
    }

    @Override
    public Order markOrderAsOutForDelivery(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if (order.getStatus() != OrderStatus.SHIPPED) {
            throw new RuntimeException("Order must be in SHIPPED status to mark as OUT_FOR_DELIVERY");
        }

        order.setStatus(OrderStatus.OUT_FOR_DELIVERY);
        order.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));

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