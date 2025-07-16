package com.rushaul.logisitcs_backend.dto;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestDTO {
    private String externalOrderId;
    private Long customerId;
    private String sellerName;
    private String sellerAddress;
    private String destinationAddress;
    private Long pickupHubId;
    private Long destinationHubId;

}
