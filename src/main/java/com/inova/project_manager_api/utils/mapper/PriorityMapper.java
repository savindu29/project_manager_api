package com.inova.project_manager_api.utils.mapper;

import com.inova.project_manager_api.dto.response.PriorityResponseDto;
import com.inova.project_manager_api.entities.Priority;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PriorityMapper {
    public PriorityResponseDto toPriorityResponseDto(Priority p) {
        return new PriorityResponseDto(
                p.getId(), p.getName(), p.getCode()
        );
    }
}
