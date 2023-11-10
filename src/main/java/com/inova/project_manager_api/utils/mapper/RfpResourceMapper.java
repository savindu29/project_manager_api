package com.inova.project_manager_api.utils.mapper;

import com.inova.project_manager_api.dto.response.RfpResourceResponseDto;
import com.inova.project_manager_api.entities.RfpResource;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class RfpResourceMapper {
    public RfpResourceResponseDto toRfpResourceResponseDto(RfpResource rfpResource) {
        return new RfpResourceResponseDto(
                rfpResource.getId(), rfpResource.getLocation(), rfpResource.getDescription()
        );
    }
}
