package com.inova.project_manager_api.dto.request;

import lombok.Data;

@Data

public class IntermediateClientRequestDto {
    private String name;

    private String country;

    private ExternalContactPersonRequestDto externalContactPerson;
}
