package com.inova.project_manager_api.controllers;

import com.inova.project_manager_api.dto.request.ProjectRequestDto;
import com.inova.project_manager_api.exceptions.ApplicationGeneralException;
import com.inova.project_manager_api.services.ProjectService;
import com.inova.project_manager_api.utils.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
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


    @PutMapping(value = "/update", params = {"id"})
    public ResponseEntity<StandardResponse> updateProject(@RequestBody ProjectRequestDto dto, @RequestParam String id) throws ApplicationGeneralException {

        try {
            int intId = Integer.parseInt(id);
            ResponseEntity<StandardResponse> project = this.projectService.updateProject(dto, intId);
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
}
