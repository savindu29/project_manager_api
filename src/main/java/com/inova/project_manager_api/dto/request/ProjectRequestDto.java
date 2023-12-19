package com.inova.project_manager_api.dto.request;

import lombok.Data;

import java.sql.Date;
import java.util.List;

@Data

public class ProjectRequestDto {
    private String name;

    private int priority;
    private int projectStatus;

    private Date initiationDate;
    private Date proposalDueDate;
    private Date proposalSubmittedDate;
    private Date proposedImplementStartDate;
    private Date proposedImplementEndDate;
    private Date actualImplementationStartDate;
    private Date actualImplementationEndDate;
    private Date actualImplementationDueDate;
    private String lessonsLearned;
    private int ProjectLead;
    private List<Integer> effortEstimators;
    private String clarificationDiscussionDetails;
    private IntermediateClientRequestDto intermediateClient;
    private GrantClientRequestDto grantClient;
    private CostRequestDto cost;
    private TodoRequestDto todo;
    private List<RfpResourceRequestDto> rfpResources;
    private List<OutputsFromInovaRequestDto> outputsFromInova;
    private List<StatusHistoryRequestDto> latestActivity;







}
