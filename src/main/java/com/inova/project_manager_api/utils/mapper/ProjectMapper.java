package com.inova.project_manager_api.utils.mapper;

import com.inova.project_manager_api.dto.response.ProjectAdvanceResponseDto;
import com.inova.project_manager_api.entities.Project;

public class ProjectMapper {
    public ProjectAdvanceResponseDto toProjectAdvanceResponseDto(Project p){
        return new ProjectAdvanceResponseDto();
    }
}
