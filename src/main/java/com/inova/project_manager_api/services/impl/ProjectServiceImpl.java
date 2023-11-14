package com.inova.project_manager_api.services.impl;


import com.inova.project_manager_api.dao.ProjectDao;
import com.inova.project_manager_api.dto.AppRequest;
import com.inova.project_manager_api.dto.paginatedData.PaginatedProjectData;
import com.inova.project_manager_api.dto.request.ProjectDetailsSubmitRequestDto;
import com.inova.project_manager_api.dto.request.ProjectRequestDto;
import com.inova.project_manager_api.dto.response.ProjectAdvanceResponseDto;
import com.inova.project_manager_api.dto.response.ProjectDetailsSubmitResponseDto;
import com.inova.project_manager_api.dto.response.ProjectSimpleResponseDto;
import com.inova.project_manager_api.entities.*;
import com.inova.project_manager_api.exceptions.ApplicationGeneralException;
import com.inova.project_manager_api.repositories.*;
import com.inova.project_manager_api.services.ProjectService;
import com.inova.project_manager_api.utils.StandardResponse;
import com.inova.project_manager_api.utils.mapper.ProjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjectRepo projectRepo;
    @Autowired
    private ProjectDao projectDao;

    private final ProjectMapper projectMapper = new ProjectMapper();

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

    @Autowired
    private ExternalContactPersonRepo externalContactPersonRepository;

    @Override
    public StandardResponse findProject(int id) {
        Optional<Project> project = projectRepo.findById(id);
        ProjectAdvanceResponseDto projectAdvanceResponseDto = projectMapper.toProjectAdvanceResponseDto(project.get());
        if (projectAdvanceResponseDto == null) {
            return new StandardResponse(
                    404,
                    "Data not found",
                    null
            );
        } else {
            return new StandardResponse(
                    200,
                    "Data ",
                    projectAdvanceResponseDto
            );
        }
    }

    @Override
    public ResponseEntity<ProjectDetailsSubmitResponseDto> projectDetailsSubmit(AppRequest<ProjectDetailsSubmitRequestDto> request) throws ApplicationGeneralException {
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

        //insert into external contact person
        ExternalContactPerson externalContactPerson = new ExternalContactPerson();

        externalContactPerson.setName(request.getData().getExternalContactPersonName());
        externalContactPerson.setDescription(request.getData().getExternalContactPersonDescription());
        externalContactPerson.setDesignation(request.getData().getExternalContactPersonDesignation());
        externalContactPerson.setMobile(request.getData().getExternalContactPersonMobile());
        externalContactPerson.setCompanyEmail(request.getData().getExternalContactPersonEmail());
        externalContactPerson.setFixTel(request.getData().getExternalContactPersonFixTel());

        ExternalContactPerson externalContactPersonEntity = this.externalContactPersonRepository.save(externalContactPerson);

        // insert into grant_client table
        GrantClient grantClient = new GrantClient();

        grantClient.setExternalContactPerson(externalContactPersonEntity);
        grantClient.setCountry(request.getData().getGrantClientCountry());
        grantClient.setName(request.getData().getGrantClientName());

        GrantClient grantClientEntity = this.grantClientRepository.save(grantClient);
        project.setGrantClient(grantClientEntity);

        // insert into intermediate_client table
        IntermediateClient intermediateClient = new IntermediateClient();

        intermediateClient.setExternalContactPerson(externalContactPersonEntity);
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

        project.setRfpResource(rfpResourceEntity);

        // Insert into todo table
        Todo todo = new Todo();

        todo.setNotes(request.getData().getToDo());

        Todo todoEntity = this.todoRepository.save(todo);
        project.setTodo(todoEntity);

        //save project entity
        Project projectEntity = this.projectRepo.save(project);

        //ResponsiblePersonInova
        Optional<ResponsiblePersonInova> responsiblePersonInova2 = this.responsiblePersonInovaRepository.findById(1);
        List<ResponsiblePersonInova> responsiblePersonInovalist = new ArrayList<>();
        responsiblePersonInovalist.add(responsiblePersonInova2.get());

        project.setEffortEstimators(responsiblePersonInovalist);

        //Insert Status History
        StatusHistory statusHistory = new StatusHistory();

        java.util.Date currentDate = new java.util.Date();

        // Set the currentDate to your statusHistory
        statusHistory.setDate(currentDate);
        statusHistory.setDescription("Project Created");
        statusHistory.setId(projectEntity.getId());


        status = "Project details successfully saved.";
        return new ResponseEntity(new ProjectDetailsSubmitResponseDto(status), HttpStatus.OK);
    }

    public StandardResponse findAllProjects(int page, int count,String searchtext) {
        List<ProjectSimpleResponseDto> allProjects = projectDao.getAllProjects(page, count,searchtext);
        PaginatedProjectData paginatedProjectData = new PaginatedProjectData(
                projectDao.getProjectCount(),
                allProjects
        );
        if (paginatedProjectData.getCount() == 0) {
            return new StandardResponse(
                    404,
                    "Data not found",
                    null
            );
        } else {
            return new StandardResponse(
                    200,
                    "Data list",
                    paginatedProjectData
            );
        }
    }

    @Override
    public StandardResponse updateProject(ProjectRequestDto dto, int id) {
        return new StandardResponse(
                200,
                "updated",
                null
        );
    }
}


