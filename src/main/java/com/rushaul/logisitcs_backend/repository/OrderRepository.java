package com.rushaul.logisitcs_backend.repository;

import com.rushaul.logisitcs_backend.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByCustomerId(Long customerId);

}
