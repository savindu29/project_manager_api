package com.inova.project_manager_api.utils.mapper;

import com.inova.project_manager_api.dto.response.OutputsFromInovaResponseDto;
import com.inova.project_manager_api.dto.response.ProjectStatusResponseDto;
import com.inova.project_manager_api.entities.OutputsFromInova;
import com.inova.project_manager_api.entities.ProjectStatus;

public class OutputsFromInovaMapper {
    public OutputsFromInovaResponseDto toOutputsFromInovaResponseDto(OutputsFromInova out) {
        return new OutputsFromInovaResponseDto(
                out.getId(),out.getLocation(),out.getDescription()
        );
    }
}
