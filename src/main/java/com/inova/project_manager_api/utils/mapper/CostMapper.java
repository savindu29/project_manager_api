package com.inova.project_manager_api.utils.mapper;

import com.inova.project_manager_api.dto.request.CostRequestDto;
import com.inova.project_manager_api.dto.response.CostResponseDto;
import com.inova.project_manager_api.entities.Cost;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;

@NoArgsConstructor
public class CostMapper {
    @Bean
    public CostResponseDto toCostResponseDto(Cost c){
        if(c==null){
            return null;
        }
        return new CostResponseDto(
                c.getId(),c.getTotalEffortMh(),c.getQuotedValue(),c.getQuotedRate(),c.getAmcValue()
        );
    }
    @Bean
    public Cost toCostEntity(CostRequestDto c){
        Cost cost = new Cost();
        cost.setAmcValue(c.getAmcValue());
        cost.setQuotedRate(c.getQuotedRate());
        cost.setQuotedValue(c.getQuotedValue());
        cost.setTotalEffortMh(c.getTotalEffortMh());
        return cost;

    }
}
