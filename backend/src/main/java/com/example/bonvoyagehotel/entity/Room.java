package com.example.bonvoyagehotel.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "rooms")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String roomNumber;

    @Column(nullable = false)
    private String type; // e.g. Single, Double, Suite

    @Column(nullable = false)
    private double pricePerNight;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String imageUrl; // Cloudinary URL

    @Column(nullable = false)
    private boolean available;
} 