package com.rushaul.logisitcs_backend.service;

import com.rushaul.logisitcs_backend.dto.DeliveryAssignmentRequestDTO;
import com.rushaul.logisitcs_backend.dto.DeliveryAssignmentResponseDTO;
import com.rushaul.logisitcs_backend.model.DeliveryAssignment;

import java.util.List;
import java.util.Optional;

public interface DeliveryAssignmentService {
    Optional<DeliveryAssignment> getAssignmentById(Long id);
    List<DeliveryAssignment> getAllAssignments();
    DeliveryAssignment updateAssignment(Long id, DeliveryAssignment assignment);
    void deleteAssignment(Long id);
    DeliveryAssignmentResponseDTO assignPersonnel(DeliveryAssignmentRequestDTO dto);
}