package com.inova.project_manager_api.services.impl;

import com.inova.project_manager_api.dao.ProjectResourceDao;
import com.inova.project_manager_api.dto.request.EmployeeNameDto;
import com.inova.project_manager_api.dto.request.ProjectResourceDto;
import com.inova.project_manager_api.dto.request.ResourceSpecificationRequestDto;
import com.inova.project_manager_api.dto.response.EmployeeResponseDto;
import com.inova.project_manager_api.dto.response.EmployeeSpecificationResponseDto;
import com.inova.project_manager_api.dto.response.SkillResponseDto;
import com.inova.project_manager_api.entities.Employee;
import com.inova.project_manager_api.entities.EmployeeSpecification;
import com.inova.project_manager_api.entities.Project;
import com.inova.project_manager_api.repositories.EmployeeRepo;
import com.inova.project_manager_api.repositories.EmployeeSpecificationRepo;
import com.inova.project_manager_api.repositories.ProjectResourceRepo;
import com.inova.project_manager_api.services.ResourceService;
import com.inova.project_manager_api.utils.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ProjectResourceRepo projectResourceRepo;
    @Autowired
    private ProjectResourceDao projectResourceDao;
    @Autowired
    private EmployeeSpecificationRepo specificationRepo;
    @Autowired
    private EmployeeRepo employeeRepo;
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

    @Override
    public StandardResponse getEmployeesBySkill(ResourceSpecificationRequestDto request) {
        List<EmployeeSpecificationResponseDto> employeesBySkill = projectResourceDao.getEmployeesBySkill(request);
        if(employeesBySkill==null){
            return new StandardResponse(
                    500,
                    "Server error ",
                    null
            );
        }
        return new StandardResponse(
                200,
                "data",
                employeesBySkill
        );
    }

    @Override
    public StandardResponse getEmployeeSkills(int employeeId) {
        Optional<Employee> byId = employeeRepo.findById(employeeId);
        if(byId.isPresent()){
            List<EmployeeSpecification> allByEmployee = specificationRepo.findAllByEmployee(byId.get());
            Stream<SkillResponseDto> skillResponseDtoStream = allByEmployee.stream().map(employeeSpecification -> toSkillResponseDto(employeeSpecification));
            return new StandardResponse(
                    200,
                    "data",
                    skillResponseDtoStream
            );
        }
        return new StandardResponse(
                400,
                "No Data",
                null
        );
    }

    private SkillResponseDto toSkillResponseDto(EmployeeSpecification employeeSpecification) {
        return SkillResponseDto.builder()
                .specification(employeeSpecification.getSpecification().getName())
                .level(employeeSpecification.getSpecificationLevel().getName())
                .build();
    }

}
