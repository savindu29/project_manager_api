package com.inova.project_manager_api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ExternalContactPersonResponseDto {
    private int id;

    private String name;

    private String mobile;

    private String fixTel;

    private String companyEmail;

    private String designation;

    private String description;
}
