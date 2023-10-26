package com.inova.project_manager_api.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "external_contact_person")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ExternalContactPerson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;


    @Column(name = "mobile")
    private String mobile;

    // Other fields

    // Constructors, getters, and setters
}
