package com.inova.project_manager_api.utils.mapper;

import com.inova.project_manager_api.dto.request.OutputsFromInovaRequestDto;
import com.inova.project_manager_api.dto.response.OutputsFromInovaResponseDto;
import com.inova.project_manager_api.entities.OutputsFromInova;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class OutputsFromInovaMapper {
    public OutputsFromInovaResponseDto toOutputsFromInovaResponseDto(OutputsFromInova out) {
        if(out==null){
            return null;
        }
        return new OutputsFromInovaResponseDto(
                out.getId(), out.getLocation(), out.getDescription()
        );
    }
    public OutputsFromInova toOutputsFromInovaEntity(OutputsFromInovaRequestDto dto){
        OutputsFromInova o =new OutputsFromInova();
        o.setDescription(dto.getDescription());
        o.setLocation(dto.getLocation());
        return o;
    }
}
