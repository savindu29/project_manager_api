package com.inova.project_manager_api.dto.request;

import com.inova.project_manager_api.dto.response.ExternalContactPersonResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

public class IntermediateClientRequestDto {
    private String name;

    private String country;

    private ExternalContactPersonRequestDto externalContactPerson;
}
