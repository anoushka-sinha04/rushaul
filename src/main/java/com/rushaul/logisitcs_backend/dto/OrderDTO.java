package com.rushaul.logisitcs_backend.dto;

import com.rushaul.logisitcs_backend.model.Order;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private String externalOrderId;
    private String sellerName;
    private String destinationAddress;
    private String status;
    private String pickupHub;
    private String destinationHub;
    private String placedAt;
    private String updatedAt;

    public static OrderDTO fromEntity(Order order) {
        return new OrderDTO(
                order.getExternalOrderId(),
                order.getSellerName(),
                order.getDestinationAddress(),
                order.getStatus().name(),
                order.getPickupHub().getName(),
                order.getDestinationHub().getName(),
                order.getPlacedAt().toString(),
                order.getUpdatedAt().toString()
        );
    }
}
