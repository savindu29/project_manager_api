package com.inova.project_manager_api.entities;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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

    @OneToMany(mappedBy = "projectStatus")
    private List<Project> projectList;


}