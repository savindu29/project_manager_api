package com.inova.project_manager_api.dto.response;

import jakarta.persistence.Column;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ImpStatusResponseDto {
    private int id;

    private String status;

    private String reason;
}
