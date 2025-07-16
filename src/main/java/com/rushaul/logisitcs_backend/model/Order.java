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

    // -------------------------------------------------- Primary Key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // -------------------------------------------------- Identifiers
    @Column(nullable = false, unique = true)
    private String externalOrderId;

    // ----------------------------------------- Customer Information
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private User customer;

    // ------------------------------- Seller and Destination Details
    private String sellerName;
    private String sellerAddress;
    private String destinationAddress;

    // -------------------------------------------- Hub Relationships
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pickup_hub_id")
    private Hub pickupHub;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destination_hub_id")
    private Hub destinationHub;

    // ------------------------------------------------------- Status
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status;

    // ---------------------------------------------------- OTP Codes
    private String otpPickup;
    private String otpDelivery;

    // --------------------------------------------------- Timestamps
    @CreationTimestamp
    private Timestamp placedAt;

    @UpdateTimestamp
    private Timestamp updatedAt;
}
