package com.rushaul.logisitcs_backend.controller;

import com.rushaul.logisitcs_backend.dto.OrderRequestDTO;
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

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequestDTO dto) {
        Order createdOrder = orderService.createOrder(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
    }

    @PostMapping("/verify-otp/pickup/{orderId}")
    public ResponseEntity<String> verifyPickupOtp(@PathVariable Long orderId, @RequestBody String otp) {
        boolean success = orderService.verifyPickupOtp(orderId, otp);
        return ResponseEntity.ok(success ? "Pickup verified." : "Invalid OTP.");
    }

    @PostMapping("/verify-otp/delivery/{orderId}")
    public ResponseEntity<String> verifyDeliveryOtp(@PathVariable Long orderId, @RequestBody String otp) {
        boolean success = orderService.verifyDeliveryOtp(orderId, otp);
        return ResponseEntity.ok(success ? "Delivery verified." : "Invalid OTP.");
    }


    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable("orderId") Long orderId) {
        return ResponseEntity.ok(orderService.getOrderById(orderId));
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Order>> getOrdersByCustomer(@PathVariable("id") Long id) {
        return ResponseEntity.ok(orderService.getOrdersByCustomer(id));
    }

    @PutMapping("/update-status/{orderId}")
    public ResponseEntity<Order> updateOrderStatus(
            @PathVariable Long orderId,
            @RequestParam OrderStatus status
    ) {
        return ResponseEntity.ok(orderService.updateOrderStatus(orderId, String.valueOf(status)));
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Order> deleteOrder(@PathVariable Long orderId) {
        return ResponseEntity.ok(orderService.deleteOrder(orderId));
    }
}
