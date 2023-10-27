package com.inova.project_manager_api.entities;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "project")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
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
    @JoinColumn(name = "priority_id")
    private Priority priority;

    @ManyToOne
    @JoinColumn(name = "project_status_id")
    private ProjectStatus projectStatus;

    @ManyToOne
    @JoinColumn(name = "status_history_id")
    private StatusHistory statusHistory;

    @ManyToOne
    @JoinColumn(name = "intermediate_client_id")
    private IntermediateClient intermediateClient;

    @ManyToOne
    @JoinColumn(name = "grant_client_id")
    private GrantClient grantClient;

    @ManyToOne
    @JoinColumn(name = "cost_id")
    private Cost cost;

    @ManyToOne
    @JoinColumn(name = "inova_project_lead")
    private ResponsiblePersonInova inovaProjectLead;

    @OneToOne
    @JoinColumn(name = "todo_id")
    private Todo todo;

    @ManyToOne
    @JoinColumn(name = "rfp_resource_id")
    private RfpResource rfpResource;

    @ManyToOne
    @JoinColumn(name = "imp_status_id")
    private ImpStatus impStatus;

    @ManyToOne
    @JoinColumn(name = "outputs_from_inova_id")
    private OutputsFromInova outputsFromInova;

    // Constructors, getters, and setters
}