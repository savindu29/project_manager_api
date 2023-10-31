package com.inova.project_manager_api.utils.mapper;

import com.inova.project_manager_api.dto.response.OutputsFromInovaResponseDto;
import com.inova.project_manager_api.entities.OutputsFromInova;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class OutputsFromInovaMapper {
    public OutputsFromInovaResponseDto toOutputsFromInovaResponseDto(OutputsFromInova out) {
        return new OutputsFromInovaResponseDto(
                out.getId(), out.getLocation(), out.getDescription()
        );
    }
}
