package com.inova.project_manager_api.utils.mapper;

import com.inova.project_manager_api.dto.response.CostResponseDto;
import com.inova.project_manager_api.entities.Cost;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;

@NoArgsConstructor
public class CostMapper {

    public CostResponseDto toCostResponseDto(Cost c){
        return new CostResponseDto(
                c.getId(),c.getTotalEffortMh(),c.getQuotedValue(),c.getQuotedRate(),c.getAmcValue()
        );
    }
}
