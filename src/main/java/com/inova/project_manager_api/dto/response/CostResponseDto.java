package com.inova.project_manager_api.dto.response;

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

    private int totalEffortMh;

    private int quotedValue;

    private int quotedRate;

    private int amcValue;

}
