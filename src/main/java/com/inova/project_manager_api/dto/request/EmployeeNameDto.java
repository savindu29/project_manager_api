package com.inova.project_manager_api.dto.request;

import com.inova.project_manager_api.entities.Employee;
import com.inova.project_manager_api.entities.Project;

import java.util.List;

public class EmployeeNameDto {

    private Long employeeId;
    private String name;
    private List<Project> allocatedProjects;
    private List<Project> pendingProjects;

    public EmployeeNameDto(Employee employee, List<Project> allocatedProjects, List<Project> pendingProjects) {
        this.employeeId = (long) employee.getId();
        this.name = employee.getName();
        this.allocatedProjects = allocatedProjects;
        this.pendingProjects = pendingProjects;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public String getName() {
        return name;
    }

    public List<Project> getPendingProjects() {
        return pendingProjects;
    }

    public void setPendingProjects(List<Project> pendingProjects) {
        this.pendingProjects = pendingProjects;
    }

    public List<Project> getAllocatedProjects() {
        return allocatedProjects;
    }

    public void setAllocatedProjects(List<Project> allocatedProjects) {
        this.allocatedProjects = allocatedProjects;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public void setName(String name) {
        this.name = name;
    }
}
