package com.rushaul.logisitcs_backend.repository;

import com.rushaul.logisitcs_backend.model.DeliveryAssignment;
import com.rushaul.logisitcs_backend.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryAssignmentRepository extends JpaRepository<DeliveryAssignment, Long> {
}
