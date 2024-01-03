package com.inova.project_manager_api.dao;

import com.inova.project_manager_api.dto.request.ResourceRequestDto;
import com.inova.project_manager_api.dto.response.ProjectResourceResponseDto;
import com.inova.project_manager_api.dto.response.ResourceAllocationResponseDto;

import java.util.List;

public interface ProjectResourceDao {
    List<ProjectResourceResponseDto> availablePercentages(ResourceRequestDto request);
    List<ProjectResourceResponseDto> availablePercentagesSum(ResourceRequestDto request);

    List<ResourceAllocationResponseDto> availablePercentagesWithProject(ResourceRequestDto request);
}
