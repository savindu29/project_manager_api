package com.inova.project_manager_api.utils.mapper;

import com.inova.project_manager_api.dto.response.ProjectAdvanceResponseDto;
import com.inova.project_manager_api.entities.GrantClient;
import com.inova.project_manager_api.entities.Project;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
@NoArgsConstructor
public class ProjectMapper {

    private final CostMapper costMapper = new CostMapper();
    private final ProjectStatusMapper  projectStatusMapper = new ProjectStatusMapper();


    private final GrantClientMapper grantClientMapper = new GrantClientMapper();


    private final PriorityMapper priorityMapper = new PriorityMapper();


    private final IntermediateClientMapper intermediateClientMapper = new IntermediateClientMapper();


    private final TodoMapper todoMapper =  new TodoMapper();


    private final RfpResourceMapper rfpResourceMapper = new RfpResourceMapper();


    private final ImpStatusMapper impStatusMapper = new ImpStatusMapper();


    private final ResponsiblePersonInovaMapper responsiblePersonInovaMapper =  new ResponsiblePersonInovaMapper();


    private final OutputsFromInovaMapper outputsFromInovaMapper = new OutputsFromInovaMapper();


    private final  StatusHistoryMapper statusHistoryMapper = new StatusHistoryMapper();


    public ProjectAdvanceResponseDto toProjectAdvanceResponseDto(Project p){
        ProjectAdvanceResponseDto pDto =  new ProjectAdvanceResponseDto();
        pDto.setId(p.getId());
        pDto.setName(p.getName());
        pDto.setInitiationDate(p.getInitiationDate());
        pDto.setProposalDueDate(p.getProposalDueDate());
        pDto.setProposalSubmittedDate(p.getProposalSubmittedDate());
        pDto.setPiStartDate(p.getPiStartDate());
        pDto.setPiEndDate(p.getPiEndDate());
        pDto.setAcStartDate(p.getAcStartDate());
        pDto.setAcEndDate(p.getAcEndDate());
        pDto.setCdDetails(p.getCdDetails());
        pDto.setLessonsLearned(p.getLessonsLearned());
        pDto.setCode(p.getCode());
        pDto.setProjectStatus(projectStatusMapper.toProjectStatusResponseDto(p.getProjectStatus()));
        pDto.setPriority(priorityMapper.toPriorityResponseDto(p.getPriority()));
        pDto.setStatusHistoryList(statusHistoryMapper.toStatusHistoryList(p.getStatusHistoryList()));
        pDto.setIntermediateClient(intermediateClientMapper.toIntermediateClientResponseDto(p.getIntermediateClient()));
        pDto.setGrantClient(grantClientMapper.toGrantClientResponseDto(p.getGrantClient()));
        pDto.setCost(costMapper.toCostResponseDto(p.getCost()));
        pDto.setTodo(todoMapper.toTodoRequestDtoResponseDto(p.getTodo()));
        pDto.setRfpResource(rfpResourceMapper.toRfpResourceResponseDto(p.getRfpResource()));
        pDto.setImpStatusList(impStatusMapper.toImpStatusResponseDtoList(p.getImpStatusList()));
        pDto.setOutputsFromInova(outputsFromInovaMapper.toOutputsFromInovaResponseDto(p.getOutputsFromInova()));
        pDto.setProjectLead(responsiblePersonInovaMapper.toResponsiblePersonInovaResponseDto(p.getProjectLead()));
        pDto.setEffortEstimators(responsiblePersonInovaMapper.toResponsiblePersonInovaResponseDtoList(p.getEffortEstimators()));

        return pDto;
    }
}
