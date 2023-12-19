package com.inova.project_manager_api.dto.request;

import lombok.Data;

@Data

public class ExternalContactPersonRequestDto {

    private String name;

    private String mobile;

    private String fixTel;

    private String companyEmail;

    private String designation;

    private String description;
}
