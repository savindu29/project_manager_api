package com.inova.project_manager_api.services;

import com.inova.project_manager_api.utils.StandardResponse;

public interface ResourceService {
    StandardResponse searchPotentialResources();
    StandardResponse getEmployeesNotAllocatedToProject(int projectId);
    StandardResponse getEmployeesAndProjectsNotAllocatedToProject(int projectId);
}
