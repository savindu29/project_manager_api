package com.inova.project_manager_api.dto.response;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class CostResponseDto {
    private int id;

    private int totalEffortMh;

    private int quotedValue;

    private int quotedRate;

    private int amcValue;
}
