package com.inova.project_manager_api.services;

import com.inova.project_manager_api.utils.StandardResponse;

public interface ResourceService {
    StandardResponse searchPotentialResources();

    StandardResponse getEmployeesAndProjectsNotAllocatedToProject(Long projectId);
}
