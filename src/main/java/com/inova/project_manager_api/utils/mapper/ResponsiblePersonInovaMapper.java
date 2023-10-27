package com.inova.project_manager_api.utils.mapper;

import com.inova.project_manager_api.dto.response.ResponsiblePersonInovaResponseDto;
import com.inova.project_manager_api.dto.response.RfpResourceResponseDto;
import com.inova.project_manager_api.entities.ResponsiblePersonInova;
import com.inova.project_manager_api.entities.RfpResource;

public class ResponsiblePersonInovaMapper {
    public ResponsiblePersonInovaResponseDto toResponsiblePersonInovaResponseDto(ResponsiblePersonInova r) {
        return new ResponsiblePersonInovaResponseDto(
                r.getId(),r.getName(),r.getMobile(),r.getCompanyEmail(),r.getPrivateEmail(),r.getDesignation(),r.getSpecializedField()
        );
    }
}
