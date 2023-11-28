package com.inova.project_manager_api.controllers;


import com.inova.project_manager_api.config.MetaDataServiceConfig;
import com.inova.project_manager_api.dto.request.ImageUploadRequestDto;
import com.inova.project_manager_api.dto.request.ProjectRequestDto;
import com.inova.project_manager_api.dto.request.ProjectUpdateRequestDto;
import com.inova.project_manager_api.dto.response.ImageUploadResponseDto;
import com.inova.project_manager_api.exceptions.ApplicationGeneralException;
import com.inova.project_manager_api.services.ProjectService;
import com.inova.project_manager_api.utils.StandardResponse;
import com.inova.project_manager_api.utils.URIPrefix;
import jakarta.validation.Valid;
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

    @Autowired
    private MetaDataServiceConfig metaDataServiceConfig;

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
            throw new ApplicationGeneralException(this.metaDataServiceConfig.getCreationFailed());
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
            throw new ApplicationGeneralException(e.getMessage());
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

    @PostMapping(URIPrefix.IMAGE_UPLOAD)
    public ResponseEntity<ImageUploadResponseDto> imageUpload(@Valid @RequestBody ImageUploadRequestDto request) throws ApplicationGeneralException {
        try {
            ResponseEntity<ImageUploadResponseDto> response = this.projectService.uploadImage(request.getProjectId(), request.getImageString(), request.getDescription());
            return response;
        } catch (Exception e) {
            throw new ApplicationGeneralException(e.getMessage());
        }
    }

}
