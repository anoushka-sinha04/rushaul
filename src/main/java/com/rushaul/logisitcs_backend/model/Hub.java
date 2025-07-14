package com.rushaul.logisitcs_backend.model;
import jakarta.persistence.*;
import lombok.*;
import java.sql.Timestamp;


@Entity
@Table(name = "hub")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Hub {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String name;

    private String location;
    private Double latitude;
    private Double longitude;

    private String contactNumber;
}
