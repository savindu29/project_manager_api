package com.inova.project_manager_api.utils.mapper;

import com.inova.project_manager_api.dto.response.ProjectStatusResponseDto;
import com.inova.project_manager_api.dto.response.StatusHistoryResponseDto;
import com.inova.project_manager_api.entities.ProjectStatus;
import com.inova.project_manager_api.entities.StatusHistory;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ProjectStatusMapper {
    public ProjectStatusResponseDto toProjectStatusResponseDto(ProjectStatus ps) {
        return new ProjectStatusResponseDto(
                ps.getId(),ps.getName(),ps.getStageCode()
        );
    }
}
