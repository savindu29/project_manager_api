package com.inova.project_manager_api.dto.response;

import com.inova.project_manager_api.entities.GrantClient;
import com.inova.project_manager_api.entities.IntermediateClient;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data

public class ExternalContactPersonResponseDto {
    private int id;

    private String name;

    private String mobile;

    private String fixTel;

    private String email;

    private String designation;

    private String description;
}
