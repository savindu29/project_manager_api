package com.inova.project_manager_api.utils.mapper;

import com.inova.project_manager_api.dto.response.IntermediateClientResponseDto;
import com.inova.project_manager_api.entities.IntermediateClient;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class IntermediateClientMapper {

    private final ExternalContactPersonMapper externalContactPersonMapper = new ExternalContactPersonMapper();

    public IntermediateClientResponseDto toIntermediateClientResponseDto(IntermediateClient i) {
        if (i == null) {
            return null;
        }
        return new IntermediateClientResponseDto(
                i.getId(),
                i.getName(),
                i.getCountry(),
                externalContactPersonMapper.toExternalContactPersonResponseDto(i.getExternalContactPerson())
        );
    }
}
