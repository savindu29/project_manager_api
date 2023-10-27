package com.inova.project_manager_api.dto.response;

import jakarta.persistence.Column;
import lombok.*;

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
