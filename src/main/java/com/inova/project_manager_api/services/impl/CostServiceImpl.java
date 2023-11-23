package com.inova.project_manager_api.services.impl;

import com.inova.project_manager_api.dto.request.CostRequestDto;
import com.inova.project_manager_api.dto.response.CostResponseDto;
import com.inova.project_manager_api.entities.Cost;
import com.inova.project_manager_api.entities.Project;
import com.inova.project_manager_api.repositories.CostRepo;
import com.inova.project_manager_api.repositories.ProjectRepo;
import com.inova.project_manager_api.services.CostService;
import com.inova.project_manager_api.utils.StandardResponse;
import com.inova.project_manager_api.utils.mapper.CostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CostServiceImpl implements CostService {
    @Autowired
    private ProjectRepo projectRepo;
    @Autowired
    private CostRepo costRepo;
    private final CostMapper costMapper = new CostMapper();

    @Override
    public ResponseEntity<StandardResponse> updateCost(CostRequestDto dto, int id) {
        try {
            Optional<Project> byId = projectRepo.findById(id);
            if (byId.isEmpty()) {
                return new ResponseEntity<>(
                        new StandardResponse(
                                404,
                                "there is no project with id = "+id,
                                null
                        ),
                        HttpStatus.INTERNAL_SERVER_ERROR
                );
            } else {
                Project  project = byId.get();
                int costId = project.getCost().getId();
                Cost cost = costRepo.findById(costId).get();
                cost.setTotalEffortMh(dto.getTotalEffortMh());
                cost.setQuotedValue(dto.getQuotedValue());
                cost.setQuotedRate(dto.getQuotedRate());
                cost.setAmcValue(dto.getAmcValue());

                Cost save = costRepo.save(cost);
                CostResponseDto costResponseDto = costMapper.toCostResponseDto(save);
                return new ResponseEntity<>(
                        new StandardResponse(
                                200,
                                "Cost Updated project id : " + id,
                                costResponseDto
                        ),
                        HttpStatus.CREATED
                );
            }
        } catch (Exception e) {
            return new ResponseEntity<>(
                    new StandardResponse(
                            500,
                            "Error occurred while saving the project: " + e.getMessage(),
                            null
                    ),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }


    }
}
