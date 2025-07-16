package com.rushaul.logisitcs_backend.controller;

import com.rushaul.logisitcs_backend.dto.DeliveryAssignmentRequestDTO;
import com.rushaul.logisitcs_backend.dto.DeliveryAssignmentResponseDTO;
import com.rushaul.logisitcs_backend.model.DeliveryAssignment;
import com.rushaul.logisitcs_backend.service.DeliveryAssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assignments")
public class DeliveryAssignmentController {

    // -------------------------------------------------- DEPENDENCIES
    private final DeliveryAssignmentService deliveryAssignmentService;

    // -------------------------------------------------- CONSTRUCTOR
    @Autowired
    public DeliveryAssignmentController(DeliveryAssignmentService deliveryAssignmentService) {
        this.deliveryAssignmentService = deliveryAssignmentService;
    }

    // -------------------------------------------------- ASSIGN DELIVERY
    @PostMapping("/assign")
    public ResponseEntity<DeliveryAssignmentResponseDTO> assignDelivery(@RequestBody DeliveryAssignmentRequestDTO dto) {
        DeliveryAssignmentResponseDTO response = deliveryAssignmentService.assignPersonnel(dto);
        return ResponseEntity.ok(response);
    }

    // -------------------------------------------------- GET ASSIGNMENT BY ID
    @GetMapping("/{id}")
    public ResponseEntity<DeliveryAssignment> getAssignmentById(@PathVariable Long id) {
        return deliveryAssignmentService.getAssignmentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // -------------------------------------------------- GET ALL ASSIGNMENTS
    @GetMapping
    public ResponseEntity<List<DeliveryAssignment>> getAllAssignments() {
        return ResponseEntity.ok(deliveryAssignmentService.getAllAssignments());
    }

    // -------------------------------------------------- UPDATE ASSIGNMENT
    @PutMapping("/{id}")
    public ResponseEntity<DeliveryAssignment> updateAssignment(@PathVariable Long id, @RequestBody DeliveryAssignment assignmentDetails) {
        return ResponseEntity.ok(deliveryAssignmentService.updateAssignment(id, assignmentDetails));
    }

    // -------------------------------------------------- DELETE ASSIGNMENT
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAssignment(@PathVariable Long id) {
        deliveryAssignmentService.deleteAssignment(id);
        return ResponseEntity.noContent().build();
    }
}
