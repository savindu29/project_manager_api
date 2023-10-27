package com.inova.project_manager_api.dto.response;

import com.inova.project_manager_api.entities.ExternalContactPerson;
import com.inova.project_manager_api.entities.Project;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class IntermediateClientResponseDto {
    private int id;

    private String name;

    private String country;

    private ExternalContactPersonResponseDto externalContactPerson;
}
