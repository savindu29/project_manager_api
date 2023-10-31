package com.inova.project_manager_api.services;

import com.inova.project_manager_api.dto.paginatedData.PaginatedProjectData;
import com.inova.project_manager_api.dto.request.ProjectRequestDto;
import com.inova.project_manager_api.dto.response.ProjectAdvanceResponseDto;

import com.inova.project_manager_api.dto.response.ProjectSimpleResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;
public interface ProjectService {
    ProjectAdvanceResponseDto findProject(int id);
    PaginatedProjectData findAllProjects(int page, int count);
    String updateProject(ProjectRequestDto dto, int id);
}
