package com.inova.project_manager_api.utils.mapper;

import com.inova.project_manager_api.dto.response.ImpStatusResponseDto;
import com.inova.project_manager_api.dto.response.ResponsiblePersonInovaResponseDto;
import com.inova.project_manager_api.dto.response.RfpResourceResponseDto;
import com.inova.project_manager_api.entities.ImpStatus;
import com.inova.project_manager_api.entities.ResponsiblePersonInova;
import com.inova.project_manager_api.entities.RfpResource;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class ResponsiblePersonInovaMapper {
    public ResponsiblePersonInovaResponseDto toResponsiblePersonInovaResponseDto(ResponsiblePersonInova r) {
        return new ResponsiblePersonInovaResponseDto(
                r.getId(),r.getName(),r.getMobile(),r.getCompanyEmail(),r.getPrivateEmail(),r.getDesignation(),r.getSpecializedField()
        );
    }
    public List<ResponsiblePersonInovaResponseDto> toResponsiblePersonInovaResponseDtoList(List<ResponsiblePersonInova> list) {
        List<ResponsiblePersonInovaResponseDto> dtoList = new ArrayList<>();
        for (ResponsiblePersonInova i: list) {
            dtoList.add(toResponsiblePersonInovaResponseDto(i));
        }
        return dtoList;
    }
}
