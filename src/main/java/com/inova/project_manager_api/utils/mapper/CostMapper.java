package com.inova.project_manager_api.utils.mapper;

import com.inova.project_manager_api.dto.response.CostResponseDto;
import com.inova.project_manager_api.entities.Cost;
import org.springframework.context.annotation.Bean;


public class CostMapper {

    public CostResponseDto toCostResponseDto(Cost c){
        return new CostResponseDto(
                c.getId(),c.getTotalEffortMh(),c.getQuotedValue(),c.getQuotedRate(),c.getAmcValue()
        );
    }
}
