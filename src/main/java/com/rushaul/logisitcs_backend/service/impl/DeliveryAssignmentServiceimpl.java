package com.rushaul.logisitcs_backend.service.impl;

import com.rushaul.logisitcs_backend.dto.DeliveryAssignmentRequestDTO;
import com.rushaul.logisitcs_backend.dto.DeliveryAssignmentResponseDTO;
import com.rushaul.logisitcs_backend.model.AssignmentStatus;
import com.rushaul.logisitcs_backend.model.DeliveryAssignment;
import com.rushaul.logisitcs_backend.model.Order;
import com.rushaul.logisitcs_backend.model.User;
import com.rushaul.logisitcs_backend.repository.DeliveryAssignmentRepository;
import com.rushaul.logisitcs_backend.repository.OrderRepository;
import com.rushaul.logisitcs_backend.repository.UserRepository;
import com.rushaul.logisitcs_backend.service.DeliveryAssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DeliveryAssignmentServiceimpl implements DeliveryAssignmentService {

    // -------------------------------------------------- DEPENDENCIES
    private final DeliveryAssignmentRepository deliveryAssignmentRepository;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    // -------------------------------------------------- CONSTRUCTOR
    @Autowired
    public DeliveryAssignmentServiceimpl(DeliveryAssignmentRepository deliveryAssignmentRepository,
                                         OrderRepository orderRepository,
                                         UserRepository userRepository) {
        this.deliveryAssignmentRepository = deliveryAssignmentRepository;
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }


    // -------------------------------------------------- ASSIGN PERSONNEL
    @Override
    public DeliveryAssignmentResponseDTO assignPersonnel(DeliveryAssignmentRequestDTO dto) {

        // Fetch Order
        Order order = orderRepository.findById(dto.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order Not Found"));

        // Fetch Personnel
        User personnel = userRepository.findById(dto.getPersonnelId())
                .orElseThrow(() -> new RuntimeException("Personnel Not Found"));

        // Create New Assignment
        DeliveryAssignment assignment = new DeliveryAssignment();
        assignment.setOrder(order);
        assignment.setPersonnel(personnel);
        assignment.setStatus(AssignmentStatus.ASSIGNED);
        assignment.setAssignedAt(Timestamp.valueOf(LocalDateTime.now()));

        // Save Assignment
        DeliveryAssignment savedAssignment = deliveryAssignmentRepository.save(assignment);

        // Return Response DTO
        return new DeliveryAssignmentResponseDTO(
                savedAssignment.getId(),
                savedAssignment.getOrder().getExternalOrderId(),
                savedAssignment.getPersonnel().getName(),
                savedAssignment.getStatus().name(),
                savedAssignment.getAssignedAt(),
                savedAssignment.getConfirmedAt(),
                savedAssignment.getCompletedAt()
        );
    }


    // -------------------------------------------------- GET ASSIGNMENT BY ID
    @Override
    public Optional<DeliveryAssignment> getAssignmentById(Long id) {
        return deliveryAssignmentRepository.findById(id);
    }


    // -------------------------------------------------- GET ALL ASSIGNMENTS
    @Override
    public List<DeliveryAssignment> getAllAssignments() {
        return deliveryAssignmentRepository.findAll();
    }


    // -------------------------------------------------- UPDATE ASSIGNMENT
    @Override
    public DeliveryAssignment updateAssignment(Long id, DeliveryAssignment assignmentDetails) {
        return deliveryAssignmentRepository.findById(id).map(assignment -> {
            assignment.setPersonnel(assignmentDetails.getPersonnel());
            assignment.setAssignedAt(assignmentDetails.getAssignedAt());
            assignment.setStatus(assignmentDetails.getStatus());
            return deliveryAssignmentRepository.save(assignment);
        }).orElseThrow(() -> new RuntimeException("Assignment not found with ID: " + id));
    }


    // -------------------------------------------------- DELETE ASSIGNMENT
    @Override
    public void deleteAssignment(Long id) {
        deliveryAssignmentRepository.deleteById(id);
    }
}
