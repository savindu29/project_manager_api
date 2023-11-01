package com.inova.project_manager_api.services.impl;

import com.inova.project_manager_api.dto.request.ResponsiblePersonInovaRequestDto;
import com.inova.project_manager_api.dto.response.ResponsiblePersonInovaResponseDto;
import com.inova.project_manager_api.entities.ResponsiblePersonInova;
import com.inova.project_manager_api.repositories.ResponsiblePersonInovaRepo;
import com.inova.project_manager_api.services.ResponsiblePersonInovaService;
import com.inova.project_manager_api.utils.mapper.ResponsiblePersonInovaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResponsiblePersonInovaServiceImpl implements ResponsiblePersonInovaService {
    @Autowired
    private ResponsiblePersonInovaRepo responsiblePersonInovaRepo;
    private final ResponsiblePersonInovaMapper responsiblePersonInovaMapper = new ResponsiblePersonInovaMapper();

    @Override
    public List<ResponsiblePersonInovaResponseDto> findAllResponsiblePersons() {
        return responsiblePersonInovaMapper.toResponsiblePersonInovaResponseDtoList(
                responsiblePersonInovaRepo.findAll()
        );
    }

    @Override
    public ResponsiblePersonInovaResponseDto saveResponsiblePerson(ResponsiblePersonInovaRequestDto dto) {
        ResponsiblePersonInova savedData = responsiblePersonInovaRepo.save(
                responsiblePersonInovaMapper.toResponsiblePersonInovaEntity(dto)
        );
        return responsiblePersonInovaMapper.toResponsiblePersonInovaResponseDto(savedData);
    }
}
