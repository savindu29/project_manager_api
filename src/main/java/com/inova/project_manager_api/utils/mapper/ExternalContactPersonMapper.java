package com.inova.project_manager_api.utils.mapper;

import com.inova.project_manager_api.dto.response.ExternalContactPersonResponseDto;
import com.inova.project_manager_api.entities.ExternalContactPerson;
import com.inova.project_manager_api.repositories.ExternalContactPersonRepo;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@NoArgsConstructor
public class ExternalContactPersonMapper {

    public ExternalContactPersonResponseDto toExternalContactPersonResponseDto(ExternalContactPerson externalContactPerson) {

        ExternalContactPersonResponseDto externalContactPersonResponseDto = new ExternalContactPersonResponseDto(
                externalContactPerson.getId(),
                externalContactPerson.getName(),
                externalContactPerson.getMobile(),
                externalContactPerson.getFixTel(),
                externalContactPerson.getCompanyEmail(),
                externalContactPerson.getDesignation(),
                externalContactPerson.getDescription()
        );
        return externalContactPersonResponseDto;
    }
}
