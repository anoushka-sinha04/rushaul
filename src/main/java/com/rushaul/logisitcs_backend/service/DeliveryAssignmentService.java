package com.rushaul.logisitcs_backend.service;

import com.rushaul.logisitcs_backend.model.DeliveryAssignment;

import java.util.List;
import java.util.Optional;

public interface DeliveryAssignmentService {
    DeliveryAssignment assignDelivery(DeliveryAssignment assignment);
    Optional<DeliveryAssignment> getAssignmentById(Long id);
    List<DeliveryAssignment> getAllAssignments();
    DeliveryAssignment updateAssignment(Long id, DeliveryAssignment assignment);
    void deleteAssignment(Long id);
}