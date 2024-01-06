package com.inova.project_manager_api.services.impl;

import com.inova.project_manager_api.dao.ProjectResourceDao;
import com.inova.project_manager_api.dto.request.EmployeeNameDto;
import com.inova.project_manager_api.dto.request.ProjectResourceDto;
import com.inova.project_manager_api.dto.response.EmployeeResponseDto;
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
    @Autowired
    private ProjectResourceDao projectResourceDao;

    @Override
    public StandardResponse searchPotentialResources() {
        return null;
    }

    @Override
    public StandardResponse getEmployeesAndProjectsNotAllocatedToProject(int projectId) {
        ProjectResourceDto projectResourceDto = new ProjectResourceDto();

        List<EmployeeResponseDto> employeesNotAllocatedToProject = projectResourceDao.findEmployeesNotAllocatedToProject(projectId);
        return new StandardResponse(
                200,
                "Data ",
                employeesNotAllocatedToProject
        );
    }

}
