package com.inova.project_manager_api.dto.request;

import com.inova.project_manager_api.entities.Employee;
import com.inova.project_manager_api.entities.Project;

import java.util.List;

public class EmployeeNameDto {

    private String name;
    private List<Project> allocatedProjects;

    public EmployeeNameDto(Employee employee, List<Project> allocatedProjects) {
        this.name = employee.getName();
        this.allocatedProjects = allocatedProjects;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Project> getAllocatedProjects() {
        return allocatedProjects;
    }

    public void setAllocatedProjects(List<Project> allocatedProjects) {
        this.allocatedProjects = allocatedProjects;
    }
}
