package com.inova.project_manager_api.utils.mapper;

import com.inova.project_manager_api.dto.response.IntermediateClientResponseDto;
import com.inova.project_manager_api.entities.IntermediateClient;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class IntermediateClientMapper {

    private final ExternalContactPersonMapper externalContactPersonMapper = new ExternalContactPersonMapper();

    public IntermediateClientResponseDto toIntermediateClientResponseDto(IntermediateClient intermediateClient) {
        if (intermediateClient == null) {
            return null;
        }
        return new IntermediateClientResponseDto(
                intermediateClient.getId(),
                intermediateClient.getName(),
                intermediateClient.getCountry(),
                externalContactPersonMapper.toExternalContactPersonResponseDto(intermediateClient.getExternalContactPerson())
        );
    }
}
