package com.inova.project_manager_api.dao;

import com.inova.project_manager_api.dto.request.ProjectResourceDto;
import com.inova.project_manager_api.dto.request.ResourceRequestDto;
import com.inova.project_manager_api.dto.request.ResourceSpecificationRequestDto;
import com.inova.project_manager_api.dto.response.*;
import com.inova.project_manager_api.entities.Employee;

import java.util.List;

public interface ProjectResourceDao {
    List<ProjectResourceDto> ResourceList();

    List<ProjectResourceResponseDto> availablePercentages(ResourceRequestDto request);

    List<ProjectResourceResponseDto> availablePercentagesSum(ResourceRequestDto request);

    List<ResourceAllocationResponseDto> availablePercentagesWithProject(ResourceRequestDto request);

    List<ProjectResourceTableResponseDto> getProjectsByResource(int employeeId);
    List<EmployeeResponseDto> findEmployeesNotAllocatedToProject(int projectId);

    List<EmployeeSpecificationResponseDto> getEmployeesBySkill(ResourceSpecificationRequestDto request);
}

