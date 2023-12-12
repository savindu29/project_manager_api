package com.inova.project_manager_api.controllers;

import com.inova.project_manager_api.dto.request.ProjectRequestDto;
import com.inova.project_manager_api.dto.request.ProjectUpdateRequestDto;
import com.inova.project_manager_api.exceptions.ApplicationGeneralException;
import com.inova.project_manager_api.services.ProjectService;
import com.inova.project_manager_api.utils.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/project")
@CrossOrigin(origins = "http://localhost:3000")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping("/{id}")
    public ResponseEntity<StandardResponse> findOne(@PathVariable int id) {
//        int intId = Integer.parseInt(id);

        return new ResponseEntity<>(

                projectService.findProject(id),
                HttpStatus.OK
        );

    }


    @PostMapping(value = "/create")
    public ResponseEntity<StandardResponse> createProject(@RequestBody ProjectRequestDto request) throws ApplicationGeneralException {
        try {
            ResponseEntity<StandardResponse> project = this.projectService.createProject(request);
            return project;
        } catch (Exception e) {
            throw new ApplicationGeneralException();
        }
    }
//    @PostMapping(URIPrefix.ADD_PROJECT_DETAILS)
//    public ResponseEntity<ProjectDetailsSubmitResponseDto> projectDetailsSubmit(@Valid @RequestBody AppRequest<ProjectDetailsSubmitRequestDto> request) throws ApplicationGeneralException {
//        try {
//            this.projectService.projectDetailsSubmit(request);
//            return new ResponseEntity<ProjectDetailsSubmitResponseDto>(response.getBody(), HttpStatus.OK);
//        } catch (Exception e) {
//            throw new ApplicationGeneralException();
//        }
//    }

    @GetMapping(value = "/search", params = {"page", "size", "searchtext"}) // localhost:8000/api/v1/customer/list (GET)
    public ResponseEntity<StandardResponse> findAllCustomer(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String searchtext
    ) {
        return new ResponseEntity<>(

                projectService.findAllProjects(page, size, searchtext)

                , HttpStatus.OK
        );

    }


    @PutMapping(value = "/update", params = {"projectId"})
    public ResponseEntity<StandardResponse> updateProject(@RequestBody ProjectUpdateRequestDto dto, @RequestParam int projectId) throws ApplicationGeneralException {

        try {

            ResponseEntity<StandardResponse> project = this.projectService.updateProject(dto, projectId);
            return project;
        } catch (Exception e) {
            throw new ApplicationGeneralException();
        }

    }

    @DeleteMapping(value = "/delete", params = {"id"})
    public ResponseEntity<StandardResponse> deleteProject(@RequestParam String id) {
        int intId = Integer.parseInt(id);
        return new ResponseEntity<>(
                projectService.deleteProject(intId),
                HttpStatus.ACCEPTED
        );

    }


    @GetMapping(value = "/proposalStats")
    public ResponseEntity<StandardResponse> getStat(){
        return projectService.getProposalStat();

    }
    @GetMapping(value = "/ImplementationStats")
    public ResponseEntity<StandardResponse> getImplStats(){
        return projectService.getImplementationStat();

    }

    @GetMapping (value = "/PropsalWonNames")
    public ResponseEntity<StandardResponse> findAllCustomer() {
        return new ResponseEntity<>(

                projectService.WonProposalName()
                , HttpStatus.OK
        );

    }
    @GetMapping (value = "/PropsalLossNames")
    public ResponseEntity<StandardResponse> LossProposalName() {
        return new ResponseEntity<>(

                projectService.LossProposalName()
                , HttpStatus.OK
        );

    }
    @GetMapping (value = "/InprogressPropsalNames")
    public ResponseEntity<StandardResponse> InprogressPropsalNames() {
        return new ResponseEntity<>(

                projectService.InprogressProposalName()
                , HttpStatus.OK
        );

    }
    @GetMapping (value = "/SucessImplenetationNames")
    public ResponseEntity<StandardResponse> SucessImplenetationNames() {
        return new ResponseEntity<>(

                projectService.WonImplementationName()
                , HttpStatus.OK
        );

    }
    @GetMapping (value = "/ImplementationFailedNames")
    public ResponseEntity<StandardResponse> ImplementationFailedNames() {
        return new ResponseEntity<>(

                projectService.LossImplementationName()
                , HttpStatus.OK
        );

    }
    @GetMapping (value = "/InprogressImplementationNames")
    public ResponseEntity<StandardResponse> InprogressImplementationNames() {
        return new ResponseEntity<>(

                projectService.InprogressImplementationName()
                , HttpStatus.OK
        );

    }



}
