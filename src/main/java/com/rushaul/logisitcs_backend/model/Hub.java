package com.rushaul.logisitcs_backend.model;

import jakarta.persistence.*;
import lombok.*;
import java.sql.Timestamp;

@Entity
@Table(name = "hubs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Hub {

    // -------------------------------------------------- Primary Key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // -------------------------------------------------- Hub Details
    @Column(nullable = false, unique = true)
    private String name;
    private String location;
    private Double latitude;
    private Double longitude;

    // ------------------------------------------ Contact Information
    @Column(name = "phone_number")
    private String phoneNumber;
}
