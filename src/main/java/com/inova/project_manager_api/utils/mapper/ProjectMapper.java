package com.inova.project_manager_api.utils.mapper;

import com.inova.project_manager_api.dto.response.ProjectAdvanceResponseDto;
import com.inova.project_manager_api.entities.GrantClient;
import com.inova.project_manager_api.entities.Project;
import org.springframework.beans.factory.annotation.Autowired;

public class ProjectMapper {
    @Autowired
    private CostMapper costMapper;

    @Autowired
    private GrantClientMapper grantClientMapper;

    @Autowired
    private PriorityMapper priorityMapper;

    @Autowired
    private IntermediateClientMapper intermediateClientMapper;

    @Autowired
    private TodoMapper todoMapper;

    @Autowired
    private RfpResourceMapper rfpResourceMapper;

    @Autowired
    private ImpStatusMapper impStatusMapper;

    @Autowired
    private ResponsiblePersonInovaMapper responsiblePersonInovaMapper;

    @Autowired
    private OutputsFromInovaMapper outputsFromInovaMapper;

    @Autowired
    private StatusHistoryMapper statusHistoryMapper;


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
        //Effort estimate list (responsible person )

        return pDto;
    }
}
