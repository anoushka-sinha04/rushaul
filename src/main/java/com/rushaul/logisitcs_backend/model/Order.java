package com.rushaul.logisitcs_backend.model;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String externalOrderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private User customer;

    private String sellerName;
    private String sellerAddress;
    private String destinationAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pickup_hub_id")
    private Hub pickupHub;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destination_hub_id")
    private Hub destinationHub;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status;

    private String otpPickup;
    private String otpDelivery;


    @CreationTimestamp
    private Timestamp placedAt;
    @UpdateTimestamp
    private Timestamp updatedAt;

}
