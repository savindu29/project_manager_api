package com.inova.project_manager_api.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @Column(name = "description")
    private String description;

    @Column(name = "dms_ref", length = 500)
    private String documentReference;

    @Column(name = "file_type")
    private String fileType;


    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;


}
