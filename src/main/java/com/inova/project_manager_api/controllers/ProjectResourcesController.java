package com.inova.project_manager_api.controllers;

import com.inova.project_manager_api.services.ProjectResourcesService;
import com.inova.project_manager_api.utils.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/projectReosurces")
@CrossOrigin(origins = "http://localhost:3000")
public class ProjectResourcesController {
    @Autowired
    ProjectResourcesService projectResourcesService;

    @GetMapping(value = "/ResourceList")
    public ResponseEntity<StandardResponse> ResourceList(@RequestParam int projectId) {
        return new ResponseEntity<>(

                projectResourcesService.resources(projectId)
                , HttpStatus.OK
        );

    }
}
