package com.inova.project_manager_api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResponsiblePersonInovaResponseDto {
    private int id;

    private String name;

    private String mobile;

    private String companyEmail;

    private String privateEmail;

    private String designation;

    private String specializedField;
}
