package com.rushaul.logisitcs_backend.dto;

import lombok.Data;

@Data
public class DeliveryAssignmentRequestDTO {
    private Long orderId;
    private Long personnelId;
}
