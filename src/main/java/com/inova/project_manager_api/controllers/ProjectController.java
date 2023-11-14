package com.inova.project_manager_api.controllers;

import com.inova.project_manager_api.config.MetaDataServiceConfig;
import com.inova.project_manager_api.dto.AppRequest;
import com.inova.project_manager_api.dto.request.ProjectDetailsSubmitRequestDto;
import com.inova.project_manager_api.dto.request.ProjectRequestDto;
import com.inova.project_manager_api.dto.response.ProjectDetailsSubmitResponseDto;
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
@RequestMapping("api/v1/auth/project")
@CrossOrigin(origins = "http://localhost:3000")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private MetaDataServiceConfig metaDataServiceConfig;

    @GetMapping("/{id}")
    public ResponseEntity<StandardResponse> findOne(@PathVariable String id) {
        int intId = Integer.parseInt(id);

        return new ResponseEntity<>(

                projectService.findProject(intId),
                HttpStatus.OK
        );

    }


    @PostMapping(URIPrefix.ADD_PROJECT_DETAILS)
    public ResponseEntity<ProjectDetailsSubmitResponseDto> projectDetailsSubmit(@Valid @RequestBody AppRequest<ProjectDetailsSubmitRequestDto> request) throws ApplicationGeneralException {
        try {
            ResponseEntity<ProjectDetailsSubmitResponseDto> response = this.projectService.projectDetailsSubmit(request);
            return new ResponseEntity<ProjectDetailsSubmitResponseDto>(response.getBody(), HttpStatus.OK);
        } catch (Exception e) {
            throw new ApplicationGeneralException(this.metaDataServiceConfig.getCreationFailed());
        }
    }

    @GetMapping(value = "/list", params = {"page", "size"}) // localhost:8000/api/v1/customer/list (GET)
    public ResponseEntity<StandardResponse> findAllCustomer(
            @RequestParam int page,
            @RequestParam int size
    ) {
        return new ResponseEntity<>(

                projectService.findAllProjects(page, size)

                , HttpStatus.OK
        );

    }


    @PutMapping(value = "/update", params = {"id"})
    public ResponseEntity<StandardResponse> updateStudent(@RequestBody ProjectRequestDto dto, @RequestParam String id) {
        int intId = Integer.parseInt(id);
        return new ResponseEntity<>(

                projectService.updateProject(dto, intId),
                HttpStatus.CREATED
        );

    }
}
