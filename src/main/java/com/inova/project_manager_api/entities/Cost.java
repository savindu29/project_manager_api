package com.inova.project_manager_api.entities;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "cost")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Cost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "total_effot_mh")
    private int totalEffortMh;

    @Column(name = "quoted_value")
    private int quotedValue;

    @Column(name = "quoted_rate")
    private int quotedRate;

    @Column(name = "amc_value")
    private int amcValue;

}
