package com.inova.project_manager_api.controllers;

import com.inova.project_manager_api.services.ResourceService;
import com.inova.project_manager_api.utils.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    @GetMapping("/employees/notAllocatedToProject")
    public ResponseEntity<StandardResponse> getEmployeesNotAllocatedToProject(@RequestParam int projectId) {
        return new ResponseEntity<>(
                resourceService.getEmployeesNotAllocatedToProject(projectId),
                HttpStatus.OK
        );
    }


}
