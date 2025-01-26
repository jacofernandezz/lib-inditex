package com.hackathon.inditex.entities;

import com.hackathon.inditex.enums.CenterStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "centers", uniqueConstraints = @UniqueConstraint(columnNames = {"latitude", "longitude"}))
public class Center {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "capacity")
    private String capacity;

    @Enumerated(EnumType.STRING)
    private CenterStatus status;
    
    @Column(name = "current_load")
    private Integer currentLoad;
    
    @Column(name = "max_capacity")
    private Integer maxCapacity;

    @Embedded
    private Coordinates coordinates;
  
}
