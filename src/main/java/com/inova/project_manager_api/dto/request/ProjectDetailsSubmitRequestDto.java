package com.inova.project_manager_api.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
//import java.util.Date;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProjectDetailsSubmitRequestDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("project_name")
    @NotNull(message = "Project name cannot be null")
    private String projectName;

    @JsonProperty("grant_client_name")
    @NotNull(message = "Grant client name cannot be null")
    private String grantClientName;

    @JsonProperty("grant_client_country")
    @NotNull(message = "Grant client name cannot be null")
    private String grantClientCountry;

    @JsonProperty("intermediate_client_name")
    private String intermediateClientName;

    @JsonProperty("initiation_date")
    private Date initiationDate;

    @JsonProperty("latest_project_status")
    private String latestProjectStatus;

    @JsonProperty("latest_project_status_date")
    private Date latestProjectStatusDate;

    @JsonProperty("client_project_lead_name")
    private String clientProjectLeadName;

    @JsonProperty("client_project_lead_mobno")
    private String clientProjectLeadMobno;

    @JsonProperty("client_project_lead_email")
    private String clientProjectLeadEmail;

    @JsonProperty("inova_project_lead_name")
    private String inovaProjectLeadName;

    @JsonProperty("inova_project_lead_mobno")
    private String inovaProjectLeadMobno;

    @JsonProperty("inova_project_lead_email")
    private String inovaProjectLeadEmail;

    @JsonProperty("inova_project_lead_designation")
    private String inovaProjectLeadDesignation;

    @JsonProperty("inova_project_lead_specialized_field")
    private String inovaProjectLeadSpecializedField;

    @JsonProperty("proposal_due_date")
    private Date proposalDueDate;

    @JsonProperty("proposal_submitted_date")
    private Date proposalSubmittedDate;

//    @JsonProperty("effort_estimator_name")
//    private String effortEstimatorName;
//
//    @JsonProperty("effort_estimator_mobno")
//    private String effortEstimatorMobno;
//
//    @JsonProperty("effort_estimator_email")
//    private String effortEstimatorEmail;
//
//    @JsonProperty("effort_estimator_designation")
//    private String effortEstimatorDesignation;

    @JsonProperty("total_effort")
    private Integer totalEffort;

    @JsonProperty("quoted_value")
    private Integer quotedValue;

    @JsonProperty("quoting_rate")
    private Integer quotingRate;

    @JsonProperty("amc_value")
    private Integer amcValue;

    @JsonProperty("proposed_implementation_start_date")
    private Date proposedImplementationStartDate;

    @JsonProperty("proposed_implementation_end_date")
    private Date proposedImplementationEndDate;

    @JsonProperty("actual_implementation_start_date")
    private Date actualImplementationStartDate;

    @JsonProperty("implementation_due_date")
    private Date implementationDueDate;

    @JsonProperty("actual_implementation_end_date")
    private Date actualImplementationEndDate;

    @JsonProperty("project_priority")
    private String projectPriority;

    @JsonProperty("project_status")
    private String projectStatus;

    @JsonProperty("to_do")
    private String toDo;

    @JsonProperty("rfp_resources_description")
    private String rfpResourcesDescription;

    @JsonProperty("rfp_resources_locations")
    private String rfpResourcesLocations;

    @JsonProperty("clarification_discussion_details")
    private String clarificationDiscussionDetails;

    @JsonProperty("outputs_from_inova_description")
    private String outputsFromInovaDescription;

    @JsonProperty("outputs_from_inova_location")
    private String outputsFromInovaLocation;

    @JsonProperty("implementation_success_or_failure_reasons")
    private String implementationSuccessOrFailureReasons;

    @JsonProperty("lessons_learned")
    private String lessonsLearned;

    @JsonProperty("external_contact_person_name")
    private String externalContactPersonName;

    @JsonProperty("external_contact_person_email")
    private String externalContactPersonEmail;

    @JsonProperty("external_contact_person_description")
    private String externalContactPersonDescription;

    @JsonProperty("external_contact_person_designation")
    private String externalContactPersonDesignation;

    @JsonProperty("external_contact_person_fix_tel")
    private String externalContactPersonFixTel;

    @JsonProperty("external_contact_person_mobile")
    private String externalContactPersonMobile;
}
