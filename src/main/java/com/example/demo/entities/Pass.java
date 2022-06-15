package com.example.demo.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Pass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double speed;

    private LocalDateTime registryDate;

    @ManyToOne
    @JoinColumn(name = "fk_car") // Chave Strangeira
    private Car car;

    @ManyToOne
    @JoinColumn(name = "fk_radar") // Chave Strangeira
    private Radar radar;

    @PrePersist
    public void create() {

    }
}
