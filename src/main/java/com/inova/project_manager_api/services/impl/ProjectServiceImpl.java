package com.inova.project_manager_api.services.impl;

import com.inova.project_manager_api.dao.ProjectDao;
import com.inova.project_manager_api.dto.paginatedData.PaginatedProjectData;
import com.inova.project_manager_api.dto.request.ProjectRequestDto;
import com.inova.project_manager_api.dto.response.ProjectAdvanceResponseDto;
import com.inova.project_manager_api.dto.response.ProjectSimpleResponseDto;
import com.inova.project_manager_api.entities.Project;
import com.inova.project_manager_api.repositories.ProjectRepo;
import com.inova.project_manager_api.services.ProjectService;
import com.inova.project_manager_api.utils.mapper.ProjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjectRepo projectRepo;
    @Autowired
    private ProjectDao projectDao;

    private final ProjectMapper projectMapper = new ProjectMapper();

    @Override
    public ProjectAdvanceResponseDto findProject(int id) {
        Optional<Project> project = projectRepo.findById(id);
        ProjectAdvanceResponseDto p = projectMapper.toProjectAdvanceResponseDto(project.get());
        if (project.isPresent()) {
            return p;
        } else {
            return null;
        }
    }

    @Override
    public PaginatedProjectData findAllProjects(int page, int count) {
        List<ProjectSimpleResponseDto> allProjects = projectDao.getAllProjects(page, count);
        return new PaginatedProjectData(
                projectDao.getProjectCount(),
                allProjects
        );

    }

    @Override
    public String updateProject(ProjectRequestDto dto, String id) {
        return projectDao.updateProject(1, 10);
    }
}