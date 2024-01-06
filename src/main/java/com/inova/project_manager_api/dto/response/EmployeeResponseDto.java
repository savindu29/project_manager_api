package com.inova.project_manager_api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EmployeeResponseDto {
    private int id;
    private String name;
    private ArrayList<ResourceProject> allocatedProjects;
    private ArrayList<ResourceProject> pendingProjects;


}
