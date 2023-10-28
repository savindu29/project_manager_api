package com.inova.project_manager_api.utils.mapper;

import com.inova.project_manager_api.dto.response.PriorityResponseDto;
import com.inova.project_manager_api.dto.response.RfpResourceResponseDto;
import com.inova.project_manager_api.entities.Priority;
import com.inova.project_manager_api.entities.RfpResource;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class RfpResourceMapper {
    public RfpResourceResponseDto toRfpResourceResponseDto(RfpResource r) {
        return new RfpResourceResponseDto(
                r.getId(),r.getLocation(),r.getDescription()
        );
    }
}
