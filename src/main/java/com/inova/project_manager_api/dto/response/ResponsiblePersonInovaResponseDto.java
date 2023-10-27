package com.inova.project_manager_api.dto.response;

import jakarta.persistence.Column;
import lombok.*;

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
