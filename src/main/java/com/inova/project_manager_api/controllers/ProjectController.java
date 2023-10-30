package com.inova.project_manager_api.controllers;

import com.inova.project_manager_api.dto.response.ProjectAdvanceResponseDto;
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
    public ResponseEntity<StandardResponse> findOne(@PathVariable String id) {
        int intId = Integer.parseInt(id);
        ProjectAdvanceResponseDto projectAdvanceResponseDto = projectService.findProject(intId);
        StandardResponse standardResponse = new StandardResponse();
        if (projectAdvanceResponseDto == null) {
            standardResponse.setCode(404);
            standardResponse.setMessage(id + " not found");
            standardResponse.setData(null);
        } else {
            standardResponse.setCode(200);
            standardResponse.setMessage(id + " found");
            standardResponse.setData(projectAdvanceResponseDto);
        }
        return new ResponseEntity<>(
                standardResponse, HttpStatus.OK
        );
    }

    @GetMapping(value = "/list", params = {"page", "size"}) // localhost:8000/api/v1/customer/list (GET)
    public ResponseEntity<StandardResponse> findAllCustomer(
            @RequestParam int page,
            @RequestParam int size
    ) {
        return new ResponseEntity<>(
                new StandardResponse(
                        200,
                        "Data list",
                        projectService.findAllProjects(page, size)

                ), HttpStatus.OK
        );

    }






}
