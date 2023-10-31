package com.inova.project_manager_api.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "project")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "initiation_date")
    private Date initiationDate;

    @Column(name = "proposal_due_date")
    private Date proposalDueDate;

    @Column(name = "proposal_submited_date")
    private Date proposalSubmittedDate;

    @Column(name = "pi_start_date")
    private Date piStartDate;

    @Column(name = "pi_end_date")
    private Date piEndDate;

    @Column(name = "ac_start_date")
    private Date acStartDate;

    @Column(name = "ac_end_date")
    private Date acEndDate;

    @Column(name = "cd_details")
    private String cdDetails;

    @Column(name = "lessons_learned")
    private String lessonsLearned;

    @Column(name = "code")
    private String code;


    @ManyToOne
    @JoinColumn(name = "priority")
    private Priority priority;

    @ManyToOne
    @JoinColumn(name = "project_status")
    private ProjectStatus projectStatus;


    @OneToMany(mappedBy = "project")
    private List<StatusHistory> statusHistoryList;

    @ManyToOne
    @JoinColumn(name = "intermediate_client_id")
    private IntermediateClient intermediateClient;

    @ManyToOne
    @JoinColumn(name = "grant_client_id")
    private GrantClient grantClient;


    @OneToOne
    @JoinColumn(name = "cost_id") // Define the foreign key here
    private Cost cost;


    @OneToOne
    @JoinColumn(name = "todo_id") // Define the foreign key here
    private Todo todo;


    @OneToOne
    @JoinColumn(name = "rfp_resource_id") // Define the foreign key here
    private RfpResource rfpResource;

    @OneToMany(mappedBy = "project")
    private List<ImpStatus> impStatusList;

    @OneToOne
    @JoinColumn(name = "outputs_from_inova_id") // Define the foreign key here
    private OutputsFromInova outputsFromInova;

    @ManyToOne
    @JoinColumn(name = "project_lead_id")
    private ResponsiblePersonInova projectLead;

    @ManyToMany
    @JoinTable(
            name = "project_effort_estimators",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "effort_estimator_id")
    )
    private List<ResponsiblePersonInova> effortEstimators;
    // In Priority.java


}