package com.inova.project_manager_api.utils.mapper;

import com.inova.project_manager_api.dto.request.RfpResourceRequestDto;
import com.inova.project_manager_api.dto.response.RfpResourceResponseDto;
import com.inova.project_manager_api.entities.RfpResource;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class RfpResourceMapper {
    public RfpResourceResponseDto toRfpResourceResponseDto(RfpResource rfpResource) {
        if(rfpResource==null){
            return null;
        }
        return new RfpResourceResponseDto(
                rfpResource.getId(), rfpResource.getLocation(), rfpResource.getDescription()
        );
    }
    public RfpResource toRfpResourceResponseEntity(RfpResourceRequestDto dto) {
        RfpResource r = new RfpResource();
        r.setDescription(dto.getDescription());
        r.setLocation(dto.getLocation());
        return r;
    }

}
