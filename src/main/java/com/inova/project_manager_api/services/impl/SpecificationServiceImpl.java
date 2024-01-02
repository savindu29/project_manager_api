package com.inova.project_manager_api.services.impl;

import com.inova.project_manager_api.dao.SpecificationDao;
import com.inova.project_manager_api.dto.response.SpecificationAreaResponseDto;
import com.inova.project_manager_api.dto.response.SpecificationResponseDto;
import com.inova.project_manager_api.entities.SpecificationArea;
import com.inova.project_manager_api.entities.Specification;
import com.inova.project_manager_api.repositories.SpecificationAreaRepo;
import com.inova.project_manager_api.repositories.SpecificationRepo;
import com.inova.project_manager_api.services.SpecificationService;
import com.inova.project_manager_api.utils.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class SpecificationServiceImpl implements SpecificationService {
    @Autowired
    private SpecificationDao SpecificationDao;
    @Autowired
    private SpecificationRepo specificationRepo;
    @Autowired
    private SpecificationAreaRepo areaRepo;
    @Override
    public StandardResponse getSpecificationsByArea(String area) {

        try{
            int areaInt = Integer.parseInt(area);
            Optional<SpecificationArea> byId = areaRepo.findById(areaInt);
            if(byId.isEmpty()){
                return new StandardResponse(
                        400,
                        "cannot find area with id : "+area ,
                        null
                );
            }

            List<Specification> allByArea = specificationRepo.findAllByArea(byId.get());
            Stream<SpecificationResponseDto> specificationResponseDtoStream = allByArea.stream().map(specification -> mapToSpecificationResponseDto(specification));
            return
                    new StandardResponse(
                            200,
                            "data " ,
                            specificationResponseDtoStream
                    );

        }catch (Throwable e) {

            return new StandardResponse(
                    500,
                    "need correct area id :  " + e.getMessage().toString(),
                    null
            );

        }
    }

    @Override
    public StandardResponse getAllSpecificationAreas() {
        try {
            List<SpecificationArea> all = areaRepo.findAll();
            Stream<SpecificationAreaResponseDto> specificationAreaResponseDtoStream = all.stream().map(specificationArea -> mapToSpecificationAreaResponseDto(specificationArea));
            return new StandardResponse(
                    200,
                    "data  " ,
                    specificationAreaResponseDtoStream
            );
        } catch (Exception e) {
            return new StandardResponse(
                    500,
                    "error  " + e.getMessage().toString(),
                    null
            );
        }
    }

    private SpecificationAreaResponseDto mapToSpecificationAreaResponseDto(SpecificationArea specificationArea) {
        return SpecificationAreaResponseDto.builder()
                .id(specificationArea.getId())
                .name(specificationArea.getName())
                .build();
    }

    private SpecificationResponseDto mapToSpecificationResponseDto(Specification specification) {
        return SpecificationResponseDto.builder()
                .id(specification.getId())
                .name(specification.getName())
                .build();
    }
}
