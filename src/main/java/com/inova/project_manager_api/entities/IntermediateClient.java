package com.inova.project_manager_api.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "intermediate_client")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class IntermediateClient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "country")
    private String country;

    @OneToMany(mappedBy = "intermediateClient")
    private List<Project> projects;


    @ManyToOne
    @JoinColumn(name = "contact_person_id")
    private ExternalContactPerson externalContactPerson;


}
