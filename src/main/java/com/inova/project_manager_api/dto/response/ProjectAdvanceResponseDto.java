package com.inova.project_manager_api.dto.response;

import com.inova.project_manager_api.entities.*;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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

    private TodoRequestDtoResponseDto todo;

    private RfpResourceResponseDto rfpResource;

    private List<ImpStatusResponseDto> impStatusList;

    private OutputsFromInovaResponseDto outputsFromInova;

    private ResponsiblePersonInovaResponseDto projectLead;

    private List<ResponsiblePersonInovaResponseDto> effortEstimators;
}
