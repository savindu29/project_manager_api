package com.inova.project_manager_api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

public class ImpStatusRequestDto {
    private String status;

    private String reason;
}
