package com.inova.project_manager_api.dto.request;

import lombok.Data;

import java.sql.Date;
import java.util.List;
@Data
public class ProjectUpdateRequestDto {
    private int projectStatus;
    private Date initiationDate;
    private int priority;

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
}
