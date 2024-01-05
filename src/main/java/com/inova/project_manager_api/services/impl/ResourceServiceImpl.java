package com.inova.project_manager_api.services.impl;

import com.inova.project_manager_api.dto.request.EmployeeNameDto;
import com.inova.project_manager_api.entities.Employee;
import com.inova.project_manager_api.entities.Project;
import com.inova.project_manager_api.repositories.ProjectResourceRepo;
import com.inova.project_manager_api.services.ResourceService;
import com.inova.project_manager_api.utils.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ProjectResourceRepo projectResourceRepo;

    @Override
    public StandardResponse searchPotentialResources() {
        return null;
    }

    @Override
    public StandardResponse getEmployeesAndProjectsNotAllocatedToProject(Long projectId) {
        List<EmployeeNameDto> employeesNotAllocated = projectResourceRepo.findEmployeesNotAllocatedToProject(projectId)
                .stream()
                .map(employee -> {
                    List<Project> allocatedProjects = projectResourceRepo.findProjectsAllocatedToEmployeeExcludingGivenProject((long) employee.getId(), projectId);
                    List<Project> pendingProjects = projectResourceRepo.findPendingProjectsForEmployeeAndProject((long) employee.getId(), projectId);

                    // Check for null and provide empty lists
                    allocatedProjects = allocatedProjects != null ? allocatedProjects : Collections.emptyList();
                    pendingProjects = pendingProjects != null ? pendingProjects : Collections.emptyList();

                    return new EmployeeNameDto(employee, allocatedProjects, pendingProjects);
                })
                .collect(Collectors.toList());

        return new StandardResponse("Success", employeesNotAllocated);
    }

}
