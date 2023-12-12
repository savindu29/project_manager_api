package com.inova.project_manager_api.services.impl;


import com.inova.project_manager_api.dao.ProjectDao;
import com.inova.project_manager_api.dto.AppRequest;
import com.inova.project_manager_api.dto.paginatedData.PaginatedProjectData;
import com.inova.project_manager_api.dto.request.*;
import com.inova.project_manager_api.dto.response.*;
import com.inova.project_manager_api.entities.*;
import com.inova.project_manager_api.exceptions.ApplicationGeneralException;
import com.inova.project_manager_api.repositories.*;
import com.inova.project_manager_api.services.ProjectService;
import com.inova.project_manager_api.utils.StandardResponse;
import com.inova.project_manager_api.utils.codeGenerator.CodeGenerator;
import com.inova.project_manager_api.utils.mapper.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
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
    private TaskRepo taskRepository;
    @Autowired
    private StatusHistoryRepo statusHistoryRepository;

    @Autowired
    private ExternalContactPersonRepo externalContactPersonRepository;
    private final StatusHistoryMapper statusHistoryMapper = new StatusHistoryMapper();
    private final CostMapper costMapper = new CostMapper();
    private final RfpResourceMapper rfpResourceMapper = new RfpResourceMapper();
    private final OutputsFromInovaMapper outputsFromInovaMapper = new OutputsFromInovaMapper();
    private final TodoMapper todoMapper = new TodoMapper();
    private final TaskMapper taskMapper = new TaskMapper();
    private final PriorityMapper priorityMapper = new PriorityMapper();
    private final ProjectStatusMapper projectStatusMapper = new ProjectStatusMapper();
    private final IntermediateClientMapper intermediateClientMapper = new IntermediateClientMapper();
    private final ExternalContactPersonMapper externalContactPersonMapper = new ExternalContactPersonMapper();
    private final GrantClientMapper grantClientMapper = new GrantClientMapper();
    private final ResponsiblePersonInovaMapper responsiblePersonInovaMapper = new ResponsiblePersonInovaMapper();

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

        return null;
    }

    public StandardResponse findAllProjects(int page, int count, String searchtext) {
        List<ProjectSimpleResponseDto> allProjects = projectDao.getAllProjects(page, count, searchtext);
        PaginatedProjectData paginatedProjectData = new PaginatedProjectData(
                projectDao.getProjectCount(searchtext),
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
    @Transactional(rollbackOn = Throwable.class)
    public ResponseEntity<StandardResponse> updateProject(ProjectUpdateRequestDto request, int id) {
        try {
            Optional<Project> optionalProject = projectRepo.findById(id);
            if (optionalProject.isPresent()) {
                Project existingProject = optionalProject.get();

                // update project Initiation Date
                existingProject.setInitiationDate(request.getInitiationDate());


                // Update project status
                int projectStatusId = request.getProjectStatus();
                Optional<ProjectStatus> projectStatus = projectStatusRepository.findById(projectStatusId);
                existingProject.setProjectStatus(projectStatus.orElse(existingProject.getProjectStatus()));

                // Update project status
                int priorityId = request.getPriority();
                Optional<Priority> priority = priorityRepository.findById(priorityId);
                existingProject.setPriority(priority.orElse(existingProject.getPriority()));

                // Update Project Dates
                existingProject.setProposalDueDate(request.getProposalDueDate());
                existingProject.setProposalSubmittedDate(request.getProposalSubmittedDate());
                existingProject.setPiStartDate(request.getProposedImplementStartDate());
                existingProject.setPiEndDate(request.getProposedImplementEndDate());

                existingProject.setAcStartDate(request.getActualImplementationStartDate());
                existingProject.setAcEndDate(request.getProposedImplementEndDate());
                existingProject.setAcImpDueDate(request.getActualImplementationDueDate());

                //Update Clarification Details
                existingProject.setCdDetails(request.getClarificationDiscussionDetails());
                //Update Lesson learned
                existingProject.setLessonsLearned(request.getLessonsLearned());

                // Update project lead  as needed
                ResponsiblePersonInova projectLead = null;
                if (request.getProjectLead() >= 0) {
                    projectLead = responsiblePersonInovaRepository.findById(request.getProjectLead()).orElse(null);
                    existingProject.setProjectLead(projectLead);
                }

                // Update effort estimators
                if(request.getEffortEstimators()==null || request.getEffortEstimators().size()==0){
                    existingProject.setEffortEstimators(null);
                }else{

                }
                List<ResponsiblePersonInova> updatedEffortEstimators = new ArrayList<>();
                if (request.getEffortEstimators().size() > 0) {
                    for (int i : request.getEffortEstimators()) {
                        Optional<ResponsiblePersonInova> byId = responsiblePersonInovaRepository.findById(i);
                        byId.ifPresent(updatedEffortEstimators::add);
                    }
                }
                existingProject.setEffortEstimators(updatedEffortEstimators);




                // Save the updated project
                Project save = projectRepo.save(existingProject);


                ProjectResponseDto projectResponseDto = projectMapper.toProjectResponseDto(save);

                return new ResponseEntity<>(
                        new StandardResponse(
                                200,
                                "Project Updated"+ id,
                                projectResponseDto
                        ),
                        HttpStatus.OK
                );

            } else {
                return new ResponseEntity<>(
                        new StandardResponse(
                                404,
                                "Not Found Project By Id : "+ id,
                                null
                        ),
                        HttpStatus.NO_CONTENT
                );
            }
        } catch (Throwable e) {

            return new ResponseEntity<>(
                    new StandardResponse(
                            500,
                            "Error occurred while updating the project: " + e.getMessage(),
                            null
                    ),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }

    }

    @Override
    @Transactional
    public StandardResponse deleteProject(int intId) {
        Optional<Project> optionalProject = projectRepo.findById(intId);

        if (optionalProject.isPresent()) {
            projectRepo.deleteProjectById(intId);
            return new StandardResponse(
                    200,
                    "Deleted project",
                    null
            );
        } else {
            return new StandardResponse(
                    404,
                    "Data not found",
                    null
            );
        }
    }


    @Override
    @Transactional(rollbackOn = Throwable.class)
    public ResponseEntity<StandardResponse> createProject(ProjectRequestDto request) {
        try {

            //      priority
            int priorityId = request.getPriority();
            Optional<Priority> priority = priorityRepository.findById(priorityId);
            //      project status
            int projectStatusId = request.getProjectStatus();
            Optional<ProjectStatus> projectStatus = projectStatusRepository.findById(projectStatusId);

            //       intermediate Client
            IntermediateClient intermediateClient = null;
            ExternalContactPerson intermediateClientContact = null;
            if (request.getIntermediateClient() != null) {
                intermediateClient = intermediateClientMapper.toIntermediateClientEntity(request.getIntermediateClient());
                if (request.getIntermediateClient().getExternalContactPerson() != null) {
                    intermediateClientContact = externalContactPersonMapper.toExternalContactPersonEntity(request.getIntermediateClient().getExternalContactPerson());
                }

            }

//        //       grant Client
            GrantClient grantClient = null;
            ExternalContactPerson grantClientContact = null;
            if (request.getGrantClient() != null) {
                grantClient = grantClientMapper.toGrantClientEntity(request.getGrantClient());
                if (request.getGrantClient().getExternalContactPerson() != null) {
                    grantClientContact = externalContactPersonMapper.toExternalContactPersonEntity(request.getGrantClient().getExternalContactPerson());
                }
            }
            if (grantClient == null || request.getGrantClient().getIsForeign()==null || request.getGrantClient().getName()==null ) {
                throw new RuntimeException("GrantClientRequestDto is null");
            }

            //        save cost
            CostRequestDto costRequestDto = request.getCost() != null ? request.getCost() : new CostRequestDto(0, 0, 0, 0);
            Cost cost = costMapper.toCostEntity(costRequestDto);


            //       responsible Person Inova (project Lead)
            ResponsiblePersonInova projectLead = null;
            if (request.getProjectLead() >= 0) {
                projectLead = responsiblePersonInovaRepository.findById(request.getProjectLead()).get();

            }

            //      effort estimators
            List<ResponsiblePersonInova> effortEstimators = new ArrayList<>();
            if (request.getEffortEstimators().size() > 0) {
                for (int i : request.getEffortEstimators()) {
                    Optional<ResponsiblePersonInova> byId = responsiblePersonInovaRepository.findById(i);
                    if (byId.isPresent()) {
                        effortEstimators.add(byId.get());
                    }
                }
            }

            //todoTask list
            Todo todo = null;
            List<Task> tasks = new ArrayList<>();
            if (request.getTodo() != null) {

                todo = todoMapper.todoEntity(request.getTodo());
                if (request.getTodo().getTasks() == null) {
                    tasks = new ArrayList<>();
                } else if (request.getTodo().getTasks().size() > 0) {
                    for (TaskRequestDto dto : request.getTodo().getTasks()) {
                        Task task = taskMapper.toTaskEntity(dto);
                        tasks.add(task);
                    }
                }

            } else {
                todo = new Todo();
                todo.setNotes("Pending ... ");
                todo.setTasks(new ArrayList<>());
            }


            Project project = new Project();

            project.setName(request.getName());
            project.setInitiationDate(request.getInitiationDate());
            project.setProposalDueDate(request.getProposalDueDate() != null ? request.getProposalDueDate() : null);
            project.setProposalSubmittedDate(request.getProposalSubmittedDate() != null ? request.getProposalSubmittedDate() : null);
            project.setPiStartDate(request.getProposedImplementStartDate() != null ? request.getProposedImplementStartDate() : null);
            project.setPiEndDate(request.getProposedImplementEndDate() != null ? request.getProposedImplementEndDate() : null);
            project.setAcStartDate(request.getActualImplementationStartDate() != null ? request.getActualImplementationStartDate() : null);
            project.setAcEndDate(request.getActualImplementationEndDate() != null ? request.getActualImplementationEndDate() : null);
            project.setAcImpDueDate(request.getActualImplementationDueDate() != null ? request.getActualImplementationDueDate() : null);
            project.setCdDetails(request.getClarificationDiscussionDetails() != null ? request.getClarificationDiscussionDetails() : null);
            project.setLessonsLearned(request.getLessonsLearned() != null ? request.getLessonsLearned() : null);


            project.setPriority(priority.get());
            project.setProjectStatus(projectStatus.get());

            project.setActiveState(true);

            if (intermediateClient != null) {
                if (intermediateClientContact != null) {
                    ExternalContactPerson externalContactPersonClient = externalContactPersonRepository.save(intermediateClientContact);
                    intermediateClient.setExternalContactPerson(externalContactPersonClient);
                }
                IntermediateClient savedIntermediateClient = intermediateClientRepository.save(intermediateClient);
                project.setIntermediateClient(savedIntermediateClient);
            }


            if (grantClient != null) {
                if (grantClientContact != null) {
                    ExternalContactPerson externalContactPersonClient = externalContactPersonRepository.save(grantClientContact);
                    grantClient.setExternalContactPerson(externalContactPersonClient);
                }
                GrantClient savedGrantClient = grantClientRepository.save(grantClient);
                project.setGrantClient(savedGrantClient);
            }
            costRepository.save(cost);
            project.setCost(cost);

            if (projectLead != null) {
                project.setProjectLead(projectLead);
            }
            if (effortEstimators.size() > 0) {
                project.setEffortEstimators(effortEstimators);
            }
            if (todo != null) {
                todoRepository.save(todo);
                project.setTodo(todo);
                if (tasks.size() > 0) {
                    for (Task t : tasks) {
                        t.setTodo(todo);
                    }
                    List<Task> tasksList = taskRepository.saveAll(tasks);
                    todo.setTasks(tasksList);
                }

            }

            project.setStatusHistoryList(new ArrayList<>());

            //save project
            Project save = projectRepo.save(project);
            String code = null;
            CodeGenerator codeGenerator = new CodeGenerator();
            code = codeGenerator.generateCode(request.getGrantClient().getName(), request.getGrantClient().getIsForeign(), priority.get().getId(), save.getId());

            Optional<Project> savedProject = projectRepo.findById(save.getId());
            savedProject.get().setCode(code);
            projectRepo.save(savedProject.get());



            //      save rfp resource
            List<RfpResource> rfpResources = null;
            if (request.getRfpResources() != null) {
                rfpResources = new ArrayList<>();
                for (RfpResourceRequestDto resource : request.getRfpResources()) {
                    RfpResource rfpResource = rfpResourceMapper.toRfpResourceEntity(resource, save);
                    rfpResources.add(rfpResource);
                }
                rfpResourceRepository.saveAll(rfpResources);
            }
            //      save outputs from inova
            List<OutputsFromInova> outputsFromInovaList = null;
            if (request.getOutputsFromInova() != null) {
                outputsFromInovaList = new ArrayList<>();
                for (OutputsFromInovaRequestDto output : request.getOutputsFromInova()) {
                    OutputsFromInova outputsFromInova = outputsFromInovaMapper.toOutputsFromInovaEntity(output, save);
                    outputsFromInovaList.add(outputsFromInova);
                }
                outputsFromInovaRepository.saveAll(outputsFromInovaList);
            }


            StatusHistory sh = new StatusHistory();
            sh.setDescription("Project Added to the System");
            sh.setDate(new Date());
            sh.setProject(save);
            statusHistoryRepository.save(sh);
            if(request.getLatestActivity()!=null )
                for (StatusHistoryRequestDto dto: request.getLatestActivity()) {
                    StatusHistory statusHistory = statusHistoryMapper.statusHistoryEntity(dto);
                    statusHistory.setProject(save);
                    statusHistoryRepository.save(statusHistory);
                }
            ProjectResponseDto projectResponseDto = projectMapper.toProjectResponseDto(projectRepo.findById(save.getId()).get());
            return new ResponseEntity<>(
                    new StandardResponse(
                            200,
                            "Project Saved",
                            projectResponseDto
                    ),
                    HttpStatus.CREATED
            );
        } catch (Throwable e) {
            // If any exception occurs, the transaction will be rolled back, and no data will be saved
            return new ResponseEntity<>(
                    new StandardResponse(
                            500,
                            "Error occurred while saving the project: " + e.getMessage(),
                            null
                    ),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @Override
    public ResponseEntity<StandardResponse> getProposalStat() {
       try{
           ProjectStatsDto stat = projectDao.getProposalStat();

           return new ResponseEntity<>(
                   new StandardResponse(
                           200,
                           "sucess " ,
                           stat
                   ),
                   HttpStatus.OK
           );

       }catch (Throwable e) {
           // If any exception occurs, the transaction will be rolled back, and no data will be saved
           return new ResponseEntity<>(
                   new StandardResponse(
                           500,
                           "Error occurred while saving the project: " + e.getMessage(),
                           null
                   ),
                   HttpStatus.INTERNAL_SERVER_ERROR
           );

       }
    }

    @Override
    public ResponseEntity<StandardResponse> getImplementationStat() {
        try{
            ProjectImplemntationDto stat = projectDao.getImplementationStat();

            return new ResponseEntity<>(
                    new StandardResponse(
                            200,
                            "sucess " ,
                            stat
                    ),
                    HttpStatus.OK
            );

        }catch (Throwable e) {
            // If any exception occurs, the transaction will be rolled back, and no data will be saved
            return new ResponseEntity<>(
                    new StandardResponse(
                            500,
                            "Error occurred while saving the project: " + e.getMessage(),
                            null
                    ),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );

        }
    }

    @Override
    public StandardResponse getLessonsLearned(int id) {
        return null;
    }

    @Override
    public StandardResponse getAllLessonsLearned() {
        return null;
    }

    @Override
    public StandardResponse WonProposalName() {
        try{
            List<ProjectStatusSimpleResponseDto> WonproposalNames = projectDao.getWonProposalName();
            return
                    new StandardResponse(
                            200,
                            "sucess " ,
                            WonproposalNames
                    );

        }catch (Throwable e) {

                    return new StandardResponse(
                            500,
                            "Error occurred while saving the project: " + e.getMessage(),
                            null
                    );

        }

    }

    @Override
    public StandardResponse LossProposalName() {
        try{
            List<ProjectStatusSimpleResponseDto> WonproposalNames = projectDao.LossProposalName();
            return
                    new StandardResponse(
                            200,
                            "sucess " ,
                            WonproposalNames
                    );

        }catch (Throwable e) {

            return new StandardResponse(
                    500,
                    "Error occurred while saving the project: " + e.getMessage(),
                    null
            );

        }

    }
    @Override
    public StandardResponse InprogressProposalName() {
        try{
            List<ProjectStatusSimpleResponseDto> InprogressproposalNames = projectDao.getInProgressProposalName();
            return
                    new StandardResponse(
                            200,
                            "sucess " ,
                            InprogressproposalNames
                    );

        }catch (Throwable e) {

            return new StandardResponse(
                    500,
                    "Error occurred while saving the project: " + e.getMessage(),
                    null
            );

        }

    }
    @Override
    public StandardResponse WonImplementationName() {
        try{
            List<ProjectStatusSimpleResponseDto> ImplementationSucessNames = projectDao.getImplSucessProjectNames();
            return
                    new StandardResponse(
                            200,
                            "sucess " ,
                            ImplementationSucessNames
                    );

        }catch (Throwable e) {

            return new StandardResponse(
                    500,
                    "Error occurred while saving the project: " + e.getMessage(),
                    null
            );

        }

    }
    @Override
    public StandardResponse LossImplementationName() {
        try{
            List<ProjectStatusSimpleResponseDto> LossImplementationName = projectDao.getImplFailedProjectNames();
            return
                    new StandardResponse(
                            200,
                            "sucess " ,
                            LossImplementationName
                    );

        }catch (Throwable e) {

            return new StandardResponse(
                    500,
                    "Error occurred while saving the project: " + e.getMessage(),
                    null
            );

        }

    }@Override
    public StandardResponse InprogressImplementationName() {
        try{
            List<ProjectStatusSimpleResponseDto> InprogressImplementationName = projectDao.getInprogressProjectNames();
            return
                    new StandardResponse(
                            200,
                            "sucess " ,
                            InprogressImplementationName
                    );

        }catch (Throwable e) {

            return new StandardResponse(
                    500,
                    "Error occurred while saving the project: " + e.getMessage(),
                    null
            );

        }

    }





}