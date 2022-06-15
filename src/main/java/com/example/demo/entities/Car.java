package com.example.demo.entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@Table(name = "cars")
@AllArgsConstructor
@NoArgsConstructor


public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "license_plate", nullable = false, unique = true)
    private String plate;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private String manufacturer;

    @Column(nullable = false)
    private Long year;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @OneToMany (mappedBy = "car")
    private List<Pass> passes;

    @PrePersist
    public void create() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }


    @PreUpdate
    public void update() {
        updatedAt = LocalDateTime.now();
    }
}