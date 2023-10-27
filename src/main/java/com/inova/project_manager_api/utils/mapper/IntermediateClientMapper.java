package com.inova.project_manager_api.utils.mapper;

import com.inova.project_manager_api.dto.response.ExternalContactPersonResponseDto;
import com.inova.project_manager_api.dto.response.IntermediateClientResponseDto;
import com.inova.project_manager_api.entities.ExternalContactPerson;
import com.inova.project_manager_api.entities.IntermediateClient;
import org.springframework.beans.factory.annotation.Autowired;


public class IntermediateClientMapper {
    @Autowired
    private ExternalContactPersonMapper externalContactPersonMapper;

    public IntermediateClientResponseDto toIntermediateClientResponseDto(IntermediateClient i) {

        return new IntermediateClientResponseDto(
                i.getId(),
                i.getName(),
                i.getCountry(),
                externalContactPersonMapper.toExternalContactPersonResponseDto(i.getExternalContactPerson())
        );
    }
}
