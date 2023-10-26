package com.inova.project_manager_api.entities;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "project_status")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ProjectStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    // Constructors, getters, and setters
}