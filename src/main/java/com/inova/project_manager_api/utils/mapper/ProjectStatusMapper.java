package com.inova.project_manager_api.utils.mapper;

import com.inova.project_manager_api.dto.response.ImpStatusResponseDto;
import com.inova.project_manager_api.dto.response.ProjectStatusResponseDto;
import com.inova.project_manager_api.dto.response.StatusHistoryResponseDto;
import com.inova.project_manager_api.entities.ImpStatus;
import com.inova.project_manager_api.entities.ProjectStatus;
import com.inova.project_manager_api.entities.StatusHistory;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class ProjectStatusMapper {
    public ProjectStatusResponseDto toProjectStatusResponseDto(ProjectStatus ps) {
        return new ProjectStatusResponseDto(
                ps.getId(),ps.getName(),ps.getStageCode()
        );
    }


    public List<ProjectStatusResponseDto> toProjectStatusResponseDtoList(List<ProjectStatus> list) {
        List<ProjectStatusResponseDto> dtoList = new ArrayList<>();
        for (ProjectStatus i : list) {
            dtoList.add(toProjectStatusResponseDto(i));
        }
        return dtoList;
    }
}
