package com.inova.project_manager_api.utils.mapper;

import com.inova.project_manager_api.dto.request.ResponsiblePersonInovaRequestDto;
import com.inova.project_manager_api.dto.response.ResponsiblePersonInovaResponseDto;
import com.inova.project_manager_api.entities.ResponsiblePersonInova;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class ResponsiblePersonInovaMapper {
    public ResponsiblePersonInovaResponseDto toResponsiblePersonInovaResponseDto(ResponsiblePersonInova responsiblePersonInova) {
        ResponsiblePersonInovaResponseDto responsiblePersonInovaResponseDto = new ResponsiblePersonInovaResponseDto(
                responsiblePersonInova.getId(),
                responsiblePersonInova.getName(),
                responsiblePersonInova.getMobile(),
                responsiblePersonInova.getCompanyEmail(),
                responsiblePersonInova.getPrivateEmail(),
                responsiblePersonInova.getDesignation(),
                responsiblePersonInova.getSpecializedField()
        );
        return responsiblePersonInovaResponseDto;
    }

    public List<ResponsiblePersonInovaResponseDto> toResponsiblePersonInovaResponseDtoList(List<ResponsiblePersonInova> list) {
        List<ResponsiblePersonInovaResponseDto> dtoList = new ArrayList<>();
        for (ResponsiblePersonInova i : list) {
            dtoList.add(toResponsiblePersonInovaResponseDto(i));
        }
        return dtoList;
    }

    public ResponsiblePersonInova toResponsiblePersonInovaEntity(ResponsiblePersonInovaRequestDto dto) {
        ResponsiblePersonInova responsiblePersonInova = new ResponsiblePersonInova();
        responsiblePersonInova.setName(dto.getName());
        responsiblePersonInova.setMobile(dto.getMobile());
        responsiblePersonInova.setCompanyEmail(dto.getCompanyEmail());
        responsiblePersonInova.setPrivateEmail(dto.getPrivateEmail());
        responsiblePersonInova.setDesignation(dto.getDesignation());
        responsiblePersonInova.setSpecializedField(dto.getSpecializedField());
        return responsiblePersonInova;

    }
}
