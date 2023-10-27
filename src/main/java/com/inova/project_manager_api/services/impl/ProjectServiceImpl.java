package com.inova.project_manager_api.services.impl;

import com.inova.project_manager_api.dto.response.ProjectAdvanceResponseDto;
import com.inova.project_manager_api.entities.Project;
import com.inova.project_manager_api.repositories.ProjectRepo;
import com.inova.project_manager_api.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjectRepo projectRepo;

    @Override
    public ProjectAdvanceResponseDto findProject(int id) {
        Optional<Project> project = projectRepo.findById(id);
        ProjectAdvanceResponseDto p = new ProjectAdvanceResponseDto();
        if (project.isPresent()) {
            return p;
        } else {
            return null;
        }
    }
}