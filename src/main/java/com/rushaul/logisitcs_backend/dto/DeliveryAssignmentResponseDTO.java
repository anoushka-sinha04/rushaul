package com.rushaul.logisitcs_backend.dto;

import java.sql.Timestamp;

public class DeliveryAssignmentResponseDTO {
    private Long id;
    private String orderExternalId;
    private String personnelName;
    private String status;
    private Timestamp assignedAt;
    private Timestamp confirmedAt;
    private Timestamp completedAt;

    // ✅ Constructor
    public DeliveryAssignmentResponseDTO(Long id, String orderExternalId, String personnelName, String status,
                                         Timestamp assignedAt, Timestamp confirmedAt, Timestamp completedAt) {
        this.id = id;
        this.orderExternalId = orderExternalId;
        this.personnelName = personnelName;
        this.status = status;
        this.assignedAt = assignedAt;
        this.confirmedAt = confirmedAt;
        this.completedAt = completedAt;
    }

    // ✅ Getters & Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderExternalId() {
        return orderExternalId;
    }

    public void setOrderExternalId(String orderExternalId) {
        this.orderExternalId = orderExternalId;
    }

    public String getPersonnelName() {
        return personnelName;
    }

    public void setPersonnelName(String personnelName) {
        this.personnelName = personnelName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getAssignedAt() {
        return assignedAt;
    }

    public void setAssignedAt(Timestamp assignedAt) {
        this.assignedAt = assignedAt;
    }

    public Timestamp getConfirmedAt() {
        return confirmedAt;
    }

    public void setConfirmedAt(Timestamp confirmedAt) {
        this.confirmedAt = confirmedAt;
    }

    public Timestamp getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(Timestamp completedAt) {
        this.completedAt = completedAt;
    }
}
