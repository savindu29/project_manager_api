package com.inova.project_manager_api.dto.response;

import lombok.Data;

import java.sql.Date;
import java.util.List;

@Data
public class ProjectAdvanceResponseDto {
    private int id;

    private String name;

    private Date initiationDate;

    private Date proposalDueDate;

    private Date proposalSubmittedDate;

    private Date piStartDate;

    private Date piEndDate;

    private Date acStartDate;

    private Date acEndDate;

    private String cdDetails;

    private String lessonsLearned;

    private String code;

    private PriorityResponseDto priority;

    private ProjectStatusResponseDto projectStatus;

    private List<StatusHistoryResponseDto> statusHistoryList;

    private IntermediateClientResponseDto intermediateClient;

    private GrantClientResponseDto grantClient;

    private CostResponseDto cost;

    private TodoResponseDto todo;

    private List<RfpResourceResponseDto> rfpResource;

    private List<ImpStatusResponseDto> impStatusList;

    private List<OutputsFromInovaResponseDto> outputsFromInova;

    private ResponsiblePersonInovaResponseDto projectLead;

    private List<ResponsiblePersonInovaResponseDto> effortEstimators;
}
