package com.inova.project_manager_api.dto.request;

import lombok.Data;

@Data

public class ResponsiblePersonInovaRequestDto {

    private String name;

    private String mobile;

    private String companyEmail;

    private String privateEmail;

    private String designation;

    private String specializedField;
}
