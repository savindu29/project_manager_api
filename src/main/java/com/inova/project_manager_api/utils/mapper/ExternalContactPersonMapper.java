package com.inova.project_manager_api.utils.mapper;

import com.inova.project_manager_api.dto.request.ExternalContactPersonRequestDto;
import com.inova.project_manager_api.dto.response.ExternalContactPersonResponseDto;
import com.inova.project_manager_api.entities.ExternalContactPerson;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ExternalContactPersonMapper {

    public ExternalContactPersonResponseDto toExternalContactPersonResponseDto(ExternalContactPerson externalContactPerson) {
        if(externalContactPerson==null){
            return null;
        }

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

    public ExternalContactPerson toExternalContactPersonEntity(ExternalContactPersonRequestDto dto){
        if(dto ==null){
            return null;
        }
        ExternalContactPerson exp = new ExternalContactPerson();
        exp.setName(dto.getName());
        exp.setMobile(dto.getMobile());
        exp.setFixTel(dto.getFixTel());
        exp.setCompanyEmail(dto.getCompanyEmail());
        exp.setDesignation(dto.getDesignation());
        exp.setDescription(dto.getDescription());

        return  exp;
    }
}
