package com.rushaul.logisitcs_backend.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    // -------------------------------------------------- Primary Key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // ---------------------------------------------- User Credentials
    @Column(nullable = false, unique = true)
    private String username;
    private String password;

    // ------------------------------------------ Personal Information
    private String name;
    private String email;
    private String address;

    @Column(name = "phone_number")
    private String phone_number;

    // ---------------------------------------------- Role Information
    @Enumerated(EnumType.STRING)
    private Role role;
}
