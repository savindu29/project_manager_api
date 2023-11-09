package com.inova.project_manager_api.services;

import com.inova.project_manager_api.dto.response.ProjectStatusResponseDto;
import com.inova.project_manager_api.utils.StandardResponse;

import java.util.List;

public interface ProjectStatusService {
    StandardResponse findAllProjectStatus();
}
