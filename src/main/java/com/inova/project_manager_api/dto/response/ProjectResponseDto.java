package com.inova.project_manager_api.dto.response;

import com.inova.project_manager_api.dto.request.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProjectResponseDto {
    private int id;
    private String name;

    private PriorityResponseDto priority;
    private ProjectStatusResponseDto projectStatus;

    private Date initiationDate;
    private Date proposalDueDate;
    private Date proposalSubmittedDate;
    private Date proposedImplementStartDate;
    private Date proposedImplementEndDate;
    private Date actualImplementationStartDate;
    private Date actualImplementationEndDate;
    private Date actualImplementationDueDate;
    private String lessonsLearned;
    private ResponsiblePersonInovaResponseDto ProjectLead;
    private String code;
    private List<ResponsiblePersonInovaResponseDto> effortEstimators;


    private String clarificationDiscussionDetails;

    private IntermediateClientResponseDto intermediateClient;
    private GrantClientResponseDto grantClient;
    private CostResponseDto cost;
    private TodoResponseDto todo;
    private RfpResourceResponseDto rfpResource;
    private OutputsFromInovaResponseDto outputsFromInova;
}
