package com.inova.project_manager_api.controllers;


import com.inova.project_manager_api.services.ProjectStatusService;
import com.inova.project_manager_api.utils.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/project-status")
@CrossOrigin(origins = "http://localhost:3000")
public class ProjectStatusController {
    @Autowired
    private ProjectStatusService projectStatusService;

    @GetMapping("/list")
    public ResponseEntity<StandardResponse> findAllProjectStatus() {
        return new ResponseEntity<>(

                        projectStatusService.findAllProjectStatus()
                , HttpStatus.OK
        );

    }
}
