package com.inova.project_manager_api.utils.mapper;

import com.inova.project_manager_api.dto.request.GrantClientRequestDto;
import com.inova.project_manager_api.dto.response.GrantClientResponseDto;
import com.inova.project_manager_api.entities.GrantClient;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class GrantClientMapper {
    private final ExternalContactPersonMapper externalContactPersonMapper = new ExternalContactPersonMapper();

    public GrantClientResponseDto toGrantClientResponseDto(GrantClient g) {
        if(g==null){
            return null;
        }

        return new GrantClientResponseDto(
                g.getId(),
                g.getName(),
                g.getCountry(),
                g.getIsForeign(),
                externalContactPersonMapper.toExternalContactPersonResponseDto(g.getExternalContactPerson())
        );
    }
    public GrantClient toGrantClientEntity(GrantClientRequestDto dto ){
        GrantClient g = new GrantClient();
        g.setName(dto.getName());
        g.setCountry(dto.getCountry());
        g.setIsForeign(dto.getIsForeign());

        return g;
    }
}
