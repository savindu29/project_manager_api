package com.inova.project_manager_api.services.impl;

import com.inova.project_manager_api.dto.request.EmployeeNameDto;
import com.inova.project_manager_api.repositories.ProjectResourceRepo;
import com.inova.project_manager_api.services.ResourceService;
import com.inova.project_manager_api.utils.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ProjectResourceRepo projectResourceRepo;

    @Override
    public StandardResponse searchPotentialResources() {
        // Logic for searching potential resources
        // ...

        return null;
    }

    @Override
    public StandardResponse getEmployeesNotAllocatedToProject(int projectId) {
        List<EmployeeNameDto> employeeNames = projectResourceRepo.findEmployeesNotAllocatedToProject(projectId)
                .stream()
                .map(EmployeeNameDto::new)
                .collect(Collectors.toList());

        return new StandardResponse("Success", employeeNames);
    }
}
