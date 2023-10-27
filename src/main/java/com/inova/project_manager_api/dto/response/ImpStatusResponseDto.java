package com.inova.project_manager_api.dto.response;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class ImpStatusResponseDto {
    private int id;

    private String status;

    private String reason;
}
