package com.inova.project_manager_api.controllers;

import com.inova.project_manager_api.dto.response.ProjectResponseDto;
import com.inova.project_manager_api.services.ProjectService;
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
    public ProjectResponseDto findOne(@PathVariable int id){
        return projectService.findProject(id);
    }
}
