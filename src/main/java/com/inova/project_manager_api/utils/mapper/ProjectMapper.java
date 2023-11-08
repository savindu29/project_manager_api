package com.inova.project_manager_api.utils.mapper;

import com.inova.project_manager_api.dto.response.ProjectAdvanceResponseDto;
import com.inova.project_manager_api.entities.ExternalContactPerson;
import com.inova.project_manager_api.entities.IntermediateClient;
import com.inova.project_manager_api.entities.Project;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ProjectMapper {

    private final CostMapper costMapper = new CostMapper();
    private final ProjectStatusMapper projectStatusMapper = new ProjectStatusMapper();


    private final GrantClientMapper grantClientMapper = new GrantClientMapper();


    private final PriorityMapper priorityMapper = new PriorityMapper();


    private final IntermediateClientMapper intermediateClientMapper = new IntermediateClientMapper();


    private final TodoMapper todoMapper = new TodoMapper();


    private final RfpResourceMapper rfpResourceMapper = new RfpResourceMapper();


    private final ImpStatusMapper impStatusMapper = new ImpStatusMapper();


    private final ResponsiblePersonInovaMapper responsiblePersonInovaMapper = new ResponsiblePersonInovaMapper();


    private final OutputsFromInovaMapper outputsFromInovaMapper = new OutputsFromInovaMapper();


    private final StatusHistoryMapper statusHistoryMapper = new StatusHistoryMapper();


    public ProjectAdvanceResponseDto toProjectAdvanceResponseDto(Project project) {
        ProjectAdvanceResponseDto projectAdvanceResponseDto = new ProjectAdvanceResponseDto();
        projectAdvanceResponseDto.setId(project.getId());
        projectAdvanceResponseDto.setName(project.getName());
        projectAdvanceResponseDto.setInitiationDate(project.getInitiationDate());
        projectAdvanceResponseDto.setProposalDueDate(project.getProposalDueDate());
        projectAdvanceResponseDto.setProposalSubmittedDate(project.getProposalSubmittedDate());
        projectAdvanceResponseDto.setPiStartDate(project.getPiStartDate());
        projectAdvanceResponseDto.setPiEndDate(project.getPiEndDate());
        projectAdvanceResponseDto.setAcStartDate(project.getAcStartDate());
        projectAdvanceResponseDto.setAcEndDate(project.getAcEndDate());
        projectAdvanceResponseDto.setCdDetails(project.getCdDetails());
        projectAdvanceResponseDto.setLessonsLearned(project.getLessonsLearned());
        projectAdvanceResponseDto.setCode(project.getCode());
        projectAdvanceResponseDto.setProjectStatus(projectStatusMapper.toProjectStatusResponseDto(project.getProjectStatus()));
        projectAdvanceResponseDto.setPriority(priorityMapper.toPriorityResponseDto(project.getPriority()));
        projectAdvanceResponseDto.setStatusHistoryList(statusHistoryMapper.toStatusHistoryList(project.getStatusHistoryList()));
        projectAdvanceResponseDto.setIntermediateClient(intermediateClientMapper.toIntermediateClientResponseDto(project.getIntermediateClient()));
        projectAdvanceResponseDto.setGrantClient(grantClientMapper.toGrantClientResponseDto(project.getGrantClient()));
        projectAdvanceResponseDto.setCost(costMapper.toCostResponseDto(project.getCost()));
        projectAdvanceResponseDto.setTodo(todoMapper.toTodoRequestDtoResponseDto(project.getTodo()));
        projectAdvanceResponseDto.setRfpResource(rfpResourceMapper.toRfpResourceResponseDto(project.getRfpResource()));
        projectAdvanceResponseDto.setImpStatusList(impStatusMapper.toImpStatusResponseDtoList(project.getImpStatusList()));
        projectAdvanceResponseDto.setOutputsFromInova(outputsFromInovaMapper.toOutputsFromInovaResponseDto(project.getOutputsFromInova()));
        projectAdvanceResponseDto.setProjectLead(responsiblePersonInovaMapper.toResponsiblePersonInovaResponseDto(project.getProjectLead()));
        projectAdvanceResponseDto.setEffortEstimators(responsiblePersonInovaMapper.toResponsiblePersonInovaResponseDtoList(project.getEffortEstimators()));

        return projectAdvanceResponseDto;
    }
}
