package com.inova.project_manager_api.dto.request;

import lombok.Data;

@Data

public class GrantClientRequestDto {
    private String name;

    private String country;

    private Boolean isForeign;

    private ExternalContactPersonRequestDto externalContactPerson;
}
