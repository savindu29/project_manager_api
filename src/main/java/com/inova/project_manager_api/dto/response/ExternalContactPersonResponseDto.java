package com.inova.project_manager_api.dto.response;

import com.inova.project_manager_api.entities.GrantClient;
import com.inova.project_manager_api.entities.IntermediateClient;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

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
