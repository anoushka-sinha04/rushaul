package com.rushaul.logisitcs_backend.controller;

import com.rushaul.logisitcs_backend.model.DeliveryAssignment;
import com.rushaul.logisitcs_backend.service.DeliveryAssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assignments")
public class DeliveryAssignmentController {

    private final DeliveryAssignmentService deliveryAssignmentService;

    @Autowired
    public DeliveryAssignmentController(DeliveryAssignmentService deliveryAssignmentService) {
        this.deliveryAssignmentService = deliveryAssignmentService;
    }

    @PostMapping
    public ResponseEntity<DeliveryAssignment> assignDelivery(@RequestBody DeliveryAssignment assignment) {
        return ResponseEntity.ok(deliveryAssignmentService.assignDelivery(assignment));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeliveryAssignment> getAssignmentById(@PathVariable Long id) {
        return deliveryAssignmentService.getAssignmentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<DeliveryAssignment>> getAllAssignments() {
        return ResponseEntity.ok(deliveryAssignmentService.getAllAssignments());
    }

    @PutMapping("/{id}")
    public ResponseEntity<DeliveryAssignment> updateAssignment(@PathVariable Long id, @RequestBody DeliveryAssignment assignmentDetails) {
        return ResponseEntity.ok(deliveryAssignmentService.updateAssignment(id, assignmentDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAssignment(@PathVariable Long id) {
        deliveryAssignmentService.deleteAssignment(id);
        return ResponseEntity.noContent().build();
    }
}
