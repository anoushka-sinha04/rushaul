package com.rushaul.logisitcs_backend.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name = "delivery_assignments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryAssignment {


    // --------------------------------------------------- Primary Key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    // ------------------------------------------------- Relationships
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personnel_id", nullable = false)
    private User personnel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;


    // --------------------------------------------- Assignment Details
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AssignmentStatus status;


    // ----------------------------------------------------- Timestamps
    @CreationTimestamp
    private Timestamp assignedAt;

    @UpdateTimestamp
    private Timestamp confirmedAt;

    private Timestamp completedAt;
}
