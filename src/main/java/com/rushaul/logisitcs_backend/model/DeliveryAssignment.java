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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personnel_id", nullable = false)
    private User personnel;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AssignmentStatus status;

    @CreationTimestamp
    private Timestamp assignedAt;
    @UpdateTimestamp
    private Timestamp confirmedAt;
    private Timestamp completedAt;
}
