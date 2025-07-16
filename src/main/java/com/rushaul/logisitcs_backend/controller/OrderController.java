package com.rushaul.logisitcs_backend.controller;

import com.rushaul.logisitcs_backend.dto.OrderDTO;
import com.rushaul.logisitcs_backend.dto.OrderRequestDTO;
import com.rushaul.logisitcs_backend.dto.OtpRequestDTO;
import com.rushaul.logisitcs_backend.model.Order;
import com.rushaul.logisitcs_backend.model.OrderStatus;
import com.rushaul.logisitcs_backend.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    // -------------------------------------------------- DEPENDENCIES
    private final OrderService orderService;

    // -------------------------------------------------- CREATE ORDER
    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequestDTO dto) {
        Order createdOrder = orderService.createOrder(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
    }

    // -------------------------------------------------- VERIFY PICKUP OTP
    @PostMapping("/verify-otp/pickup/{orderId}")
    public ResponseEntity<OrderDTO> verifyPickupOtp(@PathVariable Long orderId, @RequestBody OtpRequestDTO otpRequest) {
        try {
            Order updatedOrder = orderService.verifyPickupOtp(orderId, otpRequest.getOtp());
            return ResponseEntity.ok(OrderDTO.fromEntity(updatedOrder));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // -------------------------------------------------- VERIFY DELIVERY OTP
    @PostMapping("/verify-otp/delivery/{orderId}")
    public ResponseEntity<OrderDTO> verifyDeliveryOtp(
            @PathVariable Long orderId,
            @RequestBody OtpRequestDTO otpRequest) {
        Order updatedOrder = orderService.verifyDeliveryOtp(orderId, otpRequest.getOtp());
        return ResponseEntity.ok(OrderDTO.fromEntity(updatedOrder));
    }

    // -------------------------------------------------- MARK AS SHIPPED
    @PutMapping("/orders/{id}/ship")
    public ResponseEntity<OrderDTO> markAsShipped(@PathVariable Long id) {
        Order updatedOrder = orderService.markOrderAsShipped(id);
        return ResponseEntity.ok(OrderDTO.fromEntity(updatedOrder));
    }

    // ------------------------------- ---------- MARK AS OUT FOR DELIVERY
    @PutMapping("/orders/{id}/out-for-delivery")
    public ResponseEntity<OrderDTO> markAsOutForDelivery(@PathVariable Long id) {
        Order updatedOrder = orderService.markOrderAsOutForDelivery(id);
        return ResponseEntity.ok(OrderDTO.fromEntity(updatedOrder));
    }

    // -------------------------------------------------- GET ORDER BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable("orderId") Long orderId) {
        return ResponseEntity.ok(orderService.getOrderById(orderId));
    }

    // -------------------------------------------------- GET ORDERS BY CUSTOMER
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Order>> getOrdersByCustomer(@PathVariable("id") Long id) {
        return ResponseEntity.ok(orderService.getOrdersByCustomer(id));
    }

    // -------------------------------------------------- UPDATE ORDER STATUS
    @PutMapping("/update-status/{orderId}")
    public ResponseEntity<Order> updateOrderStatus(
            @PathVariable Long orderId,
            @RequestParam OrderStatus status
    ) {
        return ResponseEntity.ok(orderService.updateOrderStatus(orderId, String.valueOf(status)));
    }

    // -------------------------------------------------- GET ALL ORDERS
    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    // -------------------------------------------------- DELETE ORDER
    @DeleteMapping("/{id}")
    public ResponseEntity<Order> deleteOrder(@PathVariable Long orderId) {
        return ResponseEntity.ok(orderService.deleteOrder(orderId));
    }
}
