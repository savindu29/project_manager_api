package com.inova.project_manager_api.controllers;

import com.inova.project_manager_api.dto.response.ProjectResponseDto;
import com.inova.project_manager_api.services.ProjectService;
import com.inova.project_manager_api.utils.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;
    @GetMapping("/{id}")
    public StandardResponse findOne(@PathVariable int id){
        StandardResponse standardResponse = new StandardResponse(
                200,
                id+"project details",
                projectService.findProject(id)
        );
        return standardResponse;
    }
}
