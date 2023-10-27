package com.inova.project_manager_api.dto.response;

import com.inova.project_manager_api.entities.ExternalContactPerson;
import com.inova.project_manager_api.entities.Project;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class GrantClientResponseDto {
    private int id;

    private String name;

    private String country;

    private Integer isForeign;

    private ExternalContactPersonResponseDto externalContactPerson;
}
