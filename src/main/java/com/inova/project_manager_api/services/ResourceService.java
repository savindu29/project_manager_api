package com.inova.project_manager_api.services;

import com.inova.project_manager_api.dto.request.ResourceSpecificationRequestDto;
import com.inova.project_manager_api.utils.StandardResponse;

public interface ResourceService {
    StandardResponse searchPotentialResources();

    StandardResponse getEmployeesAndProjectsNotAllocatedToProject(int projectId);

    StandardResponse getEmployeesBySkill(ResourceSpecificationRequestDto request);
}
