package com.inova.project_manager_api.services;

import com.inova.project_manager_api.dto.response.ProjectStatusResponseDto;

import java.util.List;

public interface ProjectStatusService {
    List<ProjectStatusResponseDto> findAllProjectStatus();
}
