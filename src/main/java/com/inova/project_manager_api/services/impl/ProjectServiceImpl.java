package com.inova.project_manager_api.services.impl;

import com.inova.project_manager_api.dto.AppRequest;
import com.inova.project_manager_api.dto.AppResponse;
import com.inova.project_manager_api.dto.request.ProjectDetailsSubmitRequestDto;
import com.inova.project_manager_api.dto.response.ProjectDetailsSubmitResponseDto;
import com.inova.project_manager_api.dto.response.ProjectResponseDto;
import com.inova.project_manager_api.entities.*;
import com.inova.project_manager_api.exceptions.ApplicationGeneralException;
import com.inova.project_manager_api.repositories.*;
import com.inova.project_manager_api.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjectRepo projectRepo;

    @Autowired
    private CostRepo costRepository;

    @Autowired
    private PriorityRepo priorityRepository;

    @Autowired
    private GrantClientRepo grantClientRepository;

    @Autowired
    private IntermediateClientRepo intermediateClientRepository;

    @Autowired
    private OutputsFromInovaRepo outputsFromInovaRepository;

    @Autowired
    private ProjectStatusRepo projectStatusRepository;

    @Autowired
    private ResponsiblePersonInovaRepo responsiblePersonInovaRepository;

    @Autowired
    private RfpResourceRepo rfpResourceRepository;

    @Autowired
    private TodoRepo todoRepository;

    @Override
    public ProjectResponseDto findProject(int id) {
        Optional<Project> project = projectRepo.findById(id);
        ProjectResponseDto p = new ProjectResponseDto();
        p.setId(1);
        p.setName("etfb");
        if (project.isPresent()) {
            System.out.println(project.get());
        } else {
            System.out.println("empty");
        }
        return p;
    }

    @Override
    public ResponseEntity<AppResponse<ProjectDetailsSubmitResponseDto>> projectDetailsSubmit(AppRequest<ProjectDetailsSubmitRequestDto> request) throws ApplicationGeneralException {
        String status = null;

        //insert into project table
        Project project = new Project();

        project.setName(request.getData().getProjectName());
        project.setCdDetails(request.getData().getClarificationDiscussionDetails());
        //code
        project.setAcStartDate((Date) request.getData().getActualImplementationStartDate());
        project.setAcEndDate((Date) request.getData().getActualImplementationEndDate());
        project.setInitiationDate((Date) request.getData().getInitiationDate());
        project.setLessonsLearned(request.getData().getLessonsLearned());
        project.setPiStartDate((Date) request.getData().getProposedImplementationStartDate());
        project.setPiEndDate((Date) request.getData().getProposedImplementationEndDate());
        project.setProposalDueDate((Date) request.getData().getProposalDueDate());
        project.setProposalSubmittedDate((Date) request.getData().getProposalSubmittedDate());

        //insert into cost table
        Cost cost = new Cost();

        cost.setAmcValue(request.getData().getAmcValue());
        cost.setQuotedRate(request.getData().getQuotingRate());
        cost.setQuotedValue(request.getData().getQuotedValue());
        cost.setTotalEffortMh(request.getData().getTotalEffort());

        Cost costEntity = this.costRepository.save(cost);
        project.setCost(costEntity);

        // insert into grant_client table
        GrantClient grantClient = new GrantClient();

        grantClient.setCountry(request.getData().getGrantClientCountry());
        grantClient.setName(request.getData().getGrantClientName());

        GrantClient grantClientEntity = this.grantClientRepository.save(grantClient);
        project.setGrantClient(grantClientEntity);

        // insert into intermediate_client table
        IntermediateClient intermediateClient = new IntermediateClient();

        intermediateClient.setName(request.getData().getIntermediateClientName());

        IntermediateClient intermediateClientEntity = this.intermediateClientRepository.save(intermediateClient);
        project.setIntermediateClient(intermediateClientEntity);

        // insert into outputs_from_inova table
        OutputsFromInova outputsFromInova = new OutputsFromInova();

        outputsFromInova.setDescription(request.getData().getOutputsFromInovaDescription());
        outputsFromInova.setLocation(request.getData().getOutputsFromInovaLocation());

        OutputsFromInova outputsFromInovaEntity = this.outputsFromInovaRepository.save(outputsFromInova);
        project.setOutputsFromInova(outputsFromInovaEntity);

        //get project priority
        Optional<Priority> priorityEntity = this.priorityRepository.findById(1);//returns priority entity
        project.setPriority(priorityEntity.get());

        // Insert into responsible_person_inova table
        ResponsiblePersonInova responsiblePersonInova = new ResponsiblePersonInova();

        responsiblePersonInova.setName(request.getData().getInovaProjectLeadName());
        responsiblePersonInova.setMobile(request.getData().getInovaProjectLeadMobno());
        responsiblePersonInova.setDesignation(request.getData().getInovaProjectLeadDesignation());
        responsiblePersonInova.setCompanyEmail(request.getData().getInovaProjectLeadEmail());
        responsiblePersonInova.setSpecializedField(request.getData().getInovaProjectLeadSpecializedField());

        ResponsiblePersonInova responsiblePersonInovaEntity = this.responsiblePersonInovaRepository.save(responsiblePersonInova);

        //get project status
        Optional<ProjectStatus> projectStatus = this.projectStatusRepository.findById(1);//returns projectStatus entity
        project.setProjectStatus(projectStatus.get());

        // Insert into rfp_resource table
        RfpResource rfpResource = new RfpResource();

        rfpResource.setDescription(request.getData().getRfpResourcesDescription());
        rfpResource.setLocation(request.getData().getRfpResourcesLocations());

        RfpResource rfpResourceEntity = this.rfpResourceRepository.save(rfpResource);

        // Insert into todo table
        Todo todo = new Todo();

        todo.setNotes(request.getData().getToDo());

        Todo todoEntity = this.todoRepository.save(todo);
        project.setTodo(todoEntity);

        // effort estimators

        //save project entity
        Project projectEntity = this.projectRepo.save(project);

        status = "Project details successfully saved.";

        return ResponseEntity.ok(AppResponse.ok(new ProjectDetailsSubmitResponseDto(status)));
    }
}
