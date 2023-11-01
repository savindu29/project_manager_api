package com.inova.project_manager_api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

public class ResponsiblePersonInovaRequestDto {

    private String name;

    private String mobile;

    private String companyEmail;

    private String privateEmail;

    private String designation;

    private String specializedField;
}
