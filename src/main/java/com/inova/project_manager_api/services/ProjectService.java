package com.inova.project_manager_api.services;

import com.inova.project_manager_api.dto.response.ProjectAdvanceResponseDto;

public interface ProjectService {
    ProjectAdvanceResponseDto findProject(int id);
}
