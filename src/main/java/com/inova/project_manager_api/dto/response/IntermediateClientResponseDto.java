package com.inova.project_manager_api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
