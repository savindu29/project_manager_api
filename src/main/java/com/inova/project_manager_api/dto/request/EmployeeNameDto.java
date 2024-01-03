package com.inova.project_manager_api.dto.request;

import com.inova.project_manager_api.entities.Employee;

public class EmployeeNameDto {

    private String name;

    public EmployeeNameDto(Employee employee) {
        this.name = employee.getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
