package com.inova.project_manager_api.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "priority")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Priority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    // Constructors, getters, and setters
}