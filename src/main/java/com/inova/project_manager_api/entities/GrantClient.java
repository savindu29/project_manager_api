package com.inova.project_manager_api.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Entity
@Table(name = "grant_client")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class GrantClient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "country")
    private String country;

    @Column(name = "isForeign")
    private Boolean isForeign;

    @OneToMany(mappedBy = "grantClient")
    private List<Project> projects;


    @ManyToOne
    @JoinColumn(name = "contact_person_id")
    private ExternalContactPerson externalContactPerson;


}