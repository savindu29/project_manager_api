package com.inova.project_manager_api.dto.request;

import com.inova.project_manager_api.enums.WorkUnitEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CostRequestDto {
    private int totalEffort;

    private int quotedValue;

    private int quotedRate;

    private int amcValue;
    private WorkUnitEnum workUnit;
    private String currencyUnit;
}
