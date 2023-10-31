package com.inova.project_manager_api.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "outputs_from_inova")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class OutputsFromInova {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "location")
    private String location;

    @Column(name = "description")
    private String description;


}