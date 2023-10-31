package com.inova.project_manager_api.utils.mapper;

import com.inova.project_manager_api.dto.response.ExternalContactPersonResponseDto;
import com.inova.project_manager_api.entities.ExternalContactPerson;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ExternalContactPersonMapper {
    public ExternalContactPersonResponseDto toExternalContactPersonResponseDto(ExternalContactPerson e) {

        return new ExternalContactPersonResponseDto(
                e.getId(),
                e.getName(),
                e.getMobile(),
                e.getFixTel(),
                e.getCompanyEmail(),
                e.getDesignation(),
                e.getDescription()
        );
    }
}
