package com.inova.project_manager_api.entities;

import com.inova.project_manager_api.enums.WorkUnitEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


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

    @Column(name = "total_effort")
    private int totalEffort;

    @Column(name = "quoted_value")
    private int quotedValue;

    @Column(name = "quoted_rate")
    private int quotedRate;

    @Column(name = "amc_value")
    private int amcValue;

    @Column(name = "work_unit")
    private WorkUnitEnum workUnit;

    @Column(name = "currency_unit")
    private String currencyUnit;

}
