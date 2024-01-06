package com.inova.project_manager_api.dao;

import com.inova.project_manager_api.dto.request.ProjectResourceDto;
import com.inova.project_manager_api.dto.request.ResourceRequestDto;
import com.inova.project_manager_api.dto.response.ProjectResourceResponseDto;
import com.inova.project_manager_api.dto.response.ProjectResourceTableResponseDto;
import com.inova.project_manager_api.dto.response.ResourceAllocationResponseDto;
import com.inova.project_manager_api.utils.StandardResponse;

import java.sql.Date;
import java.util.List;

public interface ProjectResourceDao {
    List<ProjectResourceDto> ResourceList(Integer projectId, Date allocatedDate, Date releasedDate, String employeeName);
    List<ProjectResourceResponseDto> availablePercentages(ResourceRequestDto request);
    List<ProjectResourceResponseDto> availablePercentagesSum(ResourceRequestDto request);

    List<ResourceAllocationResponseDto> availablePercentagesWithProject(ResourceRequestDto request);
    List<ProjectResourceTableResponseDto> getProjectsByResource(int employeeId);
}
