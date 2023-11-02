package com.inova.project_manager_api.services;

import com.inova.project_manager_api.dto.request.ResponsiblePersonInovaRequestDto;
import com.inova.project_manager_api.dto.response.ResponsiblePersonInovaResponseDto;
import com.inova.project_manager_api.utils.StandardResponse;

import java.util.List;

public interface ResponsiblePersonInovaService {
    StandardResponse findAllResponsiblePersons();

    StandardResponse saveResponsiblePerson(ResponsiblePersonInovaRequestDto dto);
}
