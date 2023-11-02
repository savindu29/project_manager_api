package com.inova.project_manager_api.services.impl;

import com.inova.project_manager_api.dto.request.ResponsiblePersonInovaRequestDto;
import com.inova.project_manager_api.dto.response.ResponsiblePersonInovaResponseDto;
import com.inova.project_manager_api.entities.ResponsiblePersonInova;
import com.inova.project_manager_api.repositories.ResponsiblePersonInovaRepo;
import com.inova.project_manager_api.services.ResponsiblePersonInovaService;
import com.inova.project_manager_api.utils.StandardResponse;
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
    public StandardResponse findAllResponsiblePersons() {

        List<ResponsiblePersonInovaResponseDto> responsiblePersonInovaResponseDtos = responsiblePersonInovaMapper.toResponsiblePersonInovaResponseDtoList(
                responsiblePersonInovaRepo.findAll()
        );
        if(responsiblePersonInovaResponseDtos.size()>0){
            return new StandardResponse(
                    200,
                    "Data list",
                    responsiblePersonInovaResponseDtos
            );
        }else{

            return new StandardResponse(
                    404,
                    "Data not found",
                    null
            );
        }
    }

    @Override
    public StandardResponse saveResponsiblePerson(ResponsiblePersonInovaRequestDto dto) {
        ResponsiblePersonInova savedData = responsiblePersonInovaRepo.save(
                responsiblePersonInovaMapper.toResponsiblePersonInovaEntity(dto)
        );
        ResponsiblePersonInovaResponseDto responsiblePersonInovaResponseDto = responsiblePersonInovaMapper.toResponsiblePersonInovaResponseDto(savedData);
        if(responsiblePersonInovaResponseDto==null){
            return new StandardResponse(
                    500,
                    "Server error",
                    null
            );
        }else{
            return new StandardResponse(
                    200,
                    "Saved Data",
                    responsiblePersonInovaResponseDto
            );
        }
    }
}
