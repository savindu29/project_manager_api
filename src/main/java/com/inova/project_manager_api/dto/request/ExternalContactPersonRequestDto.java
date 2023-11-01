package com.inova.project_manager_api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

public class ExternalContactPersonRequestDto {

    private String name;

    private String mobile;

    private String fixTel;

    private String companyEmail;

    private String designation;

    private String description;
}
