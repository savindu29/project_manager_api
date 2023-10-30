package com.inova.project_manager_api.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "mst_project_status")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class ProjectStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    //    Proposal Inprogress
    //    Proposal Won
    //    Proposal Lost
    //    Implementation Inprogress
    //    Implementation Successful
    //    Implementation Failed (According to project constratints)

    @Column(name = "name")
    private String name;
    //    Prop-Inprog
    //    Prop-Won
    //    Prop-Lost
    //    Impl-InProg
    //    Impl-Succes
    //    Impl-Failed
    @Column(name = "code")
    private String code;

    //    Prop-Inprog - 0
    //    Prop-Won -1
    //    Prop-Lost-99
    //    Impl-InProg -2
    //    Impl-Succes-4
    //    Impl-Failed-98

    @Column(name = "stage_code")
    private int stageCode;

    @OneToMany(mappedBy = "projectStatus")
    private List<Project> projectList;


}