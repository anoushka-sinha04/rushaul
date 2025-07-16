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

    private final DeliveryAssignmentRepository deliveryAssignmentRepository;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    @Autowired
    public DeliveryAssignmentServiceimpl(DeliveryAssignmentRepository deliveryAssignmentRepository,
                                         OrderRepository orderRepository,
                                         UserRepository userRepository) {
        this.deliveryAssignmentRepository = deliveryAssignmentRepository;
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    @Override
    public DeliveryAssignmentResponseDTO assignPersonnel(DeliveryAssignmentRequestDTO dto) {
        Order order = orderRepository.findById(dto.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order Not Found"));
        User personnel = userRepository.findById(dto.getPersonnelId())
                .orElseThrow(() -> new RuntimeException("Personnel Not Found"));

        DeliveryAssignment assignment = new DeliveryAssignment();
        assignment.setOrder(order);
        assignment.setPersonnel(personnel);
        assignment.setStatus(AssignmentStatus.ASSIGNED);
        assignment.setAssignedAt(Timestamp.valueOf(LocalDateTime.now()));

        DeliveryAssignment savedAssignment = deliveryAssignmentRepository.save(assignment);

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
