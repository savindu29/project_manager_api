package com.inova.project_manager_api.dto.response;

import com.inova.project_manager_api.enums.WorkUnitEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class CostResponseDto {
    private int id;

    private int totalEffort;

    private int quotedValue;

    private int quotedRate;

    private int amcValue;

    private WorkUnitEnum workUnit;
    private String currencyUnit;

}
