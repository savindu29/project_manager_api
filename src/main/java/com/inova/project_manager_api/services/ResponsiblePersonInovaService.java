package com.inova.project_manager_api.services;

import com.inova.project_manager_api.dto.request.ResponsiblePersonInovaRequestDto;
import com.inova.project_manager_api.dto.response.ResponsiblePersonInovaResponseDto;

import java.util.List;

public interface ResponsiblePersonInovaService {
    List<ResponsiblePersonInovaResponseDto> findAllResponsiblePersons();

    ResponsiblePersonInovaResponseDto saveResponsiblePerson(ResponsiblePersonInovaRequestDto dto);
}
