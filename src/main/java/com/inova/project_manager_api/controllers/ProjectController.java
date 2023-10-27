package com.inova.project_manager_api.controllers;

import com.inova.project_manager_api.dto.response.ProjectAdvanceResponseDto;
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
    public StandardResponse findOne(@PathVariable int id) {
        ProjectAdvanceResponseDto projectAdvanceResponseDto = projectService.findProject(id);
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
        return standardResponse;
    }
}
