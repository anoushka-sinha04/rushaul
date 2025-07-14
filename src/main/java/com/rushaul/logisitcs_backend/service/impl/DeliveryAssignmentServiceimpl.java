package com.rushaul.logisitcs_backend.service.impl;

import com.rushaul.logisitcs_backend.model.DeliveryAssignment;
import com.rushaul.logisitcs_backend.repository.DeliveryAssignmentRepository;
import com.rushaul.logisitcs_backend.service.DeliveryAssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeliveryAssignmentServiceimpl implements DeliveryAssignmentService {

    private final DeliveryAssignmentRepository deliveryAssignmentRepository;

    @Autowired
    public DeliveryAssignmentServiceimpl(DeliveryAssignmentRepository deliveryAssignmentRepository) {
        this.deliveryAssignmentRepository = deliveryAssignmentRepository;
    }

    @Override
    public DeliveryAssignment assignDelivery(DeliveryAssignment assignment) {
        return deliveryAssignmentRepository.save(assignment);
    }

    @Override
    public Optional<DeliveryAssignment> getAssignmentById(Long id) {
        return deliveryAssignmentRepository.findById(id);
    }

    @Override
    public List<DeliveryAssignment> getAllAssignments() {
        return deliveryAssignmentRepository.findAll();
    }

    @Override
    public DeliveryAssignment updateAssignment(Long id, DeliveryAssignment assignmentDetails) {
        return deliveryAssignmentRepository.findById(id).map(assignment -> {
            assignment.setPersonnel(assignmentDetails.getPersonnel());
            assignment.setAssignedAt(assignmentDetails.getAssignedAt());
            assignment.setStatus(assignmentDetails.getStatus());
            return deliveryAssignmentRepository.save(assignment);
        }).orElseThrow(() -> new RuntimeException("Assignment not found with ID: " + id));
    }

    @Override
    public void deleteAssignment(Long id) {
        deliveryAssignmentRepository.deleteById(id);
    }
}
