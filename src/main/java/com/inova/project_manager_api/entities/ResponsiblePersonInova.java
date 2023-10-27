package com.inova.project_manager_api.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@Table(name = "responsible_person_inova")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class ResponsiblePersonInova {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "company_email")
    private String companyEmail;

    @Column(name = "private_email")
    private String privateEmail;

    @Column(name = "designation")
    private String designation;

    @Column(name = "specialized_field")
    private String specializedField;

    @OneToMany(mappedBy = "projectLead")
    private List<Project> leadProjects;

    @ManyToMany(mappedBy = "effortEstimators")
    private List<Project> projectsEffortEstimated;

}