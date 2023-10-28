package com.inova.project_manager_api.utils.mapper;

import com.inova.project_manager_api.dto.response.GrantClientResponseDto;
import com.inova.project_manager_api.dto.response.IntermediateClientResponseDto;
import com.inova.project_manager_api.entities.GrantClient;
import com.inova.project_manager_api.entities.IntermediateClient;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
@NoArgsConstructor
public class GrantClientMapper {
    private final ExternalContactPersonMapper externalContactPersonMapper = new ExternalContactPersonMapper();

    public GrantClientResponseDto toGrantClientResponseDto(GrantClient g) {

        return new GrantClientResponseDto(
                g.getId(),
                g.getName(),
                g.getCountry(),
                g.getIsForeign(),
                externalContactPersonMapper.toExternalContactPersonResponseDto(g.getExternalContactPerson())
        );
    }
}
