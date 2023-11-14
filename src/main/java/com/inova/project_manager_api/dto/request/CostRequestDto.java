package com.inova.project_manager_api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class CostRequestDto {
    private int totalEffortMh;

    private int quotedValue;

    private int quotedRate;

    private int amcValue;
}
