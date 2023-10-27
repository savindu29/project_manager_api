package com.inova.project_manager_api.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "rfp_resource")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class RfpResource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "location")
    private String location;

    @Column(name = "description")
    private String description;



}
