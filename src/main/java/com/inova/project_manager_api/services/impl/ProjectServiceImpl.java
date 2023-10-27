package com.inova.project_manager_api.services.impl;

import com.inova.project_manager_api.dto.response.ProjectResponseDto;
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
    public ProjectResponseDto findProject(int id) {
        Optional<Project> project = projectRepo.findById(id);
        ProjectResponseDto p = new ProjectResponseDto();
        p.setId(1);
        p.setName("etfb");
        if (project.isPresent()) {
            System.out.println(project.get());
        } else {
            System.out.println("empty");
        }
        return p;
    }
}
