package com.inova.project_manager_api.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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

    @Column(name = "fix_tel")
    private String fixTel;
    @Column(name = "email")
    private String email;
    @Column(name = "designation")
    private String designation;
    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "externalContactPerson")
    private List<GrantClient> grantClients;

    @OneToMany(mappedBy = "externalContactPerson")
    private List<IntermediateClient> intermediateClients;




}
