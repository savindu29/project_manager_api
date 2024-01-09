package com.inova.project_manager_api.utils.mapper;

import com.inova.project_manager_api.dto.request.ProjectStatusRequestDto;
import com.inova.project_manager_api.dto.response.ProjectStatusResponseDto;
import com.inova.project_manager_api.entities.ProjectStatus;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class ProjectStatusMapper {
    public ProjectStatusResponseDto toProjectStatusResponseDto(ProjectStatus ps) {
        return new ProjectStatusResponseDto(
                ps.getId(), ps.getName(), ps.getStageCode()
        );
    }


    public ProjectStatus toProjectStatusEntity(ProjectStatusRequestDto dto){
        ProjectStatus ps =new ProjectStatus();
        ps.setName(dto.getName());
        ps.setStageCode(dto.getStageCode());
        return ps;

    }
    public List<ProjectStatusResponseDto> toProjectStatusResponseDtoList(List<ProjectStatus> list) {
        List<ProjectStatusResponseDto> dtoList = new ArrayList<>();
        for (ProjectStatus i : list) {
            dtoList.add(toProjectStatusResponseDto(i));
        }
        return dtoList;
    }
}
   