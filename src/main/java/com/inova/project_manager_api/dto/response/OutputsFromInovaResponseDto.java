package com.inova.project_manager_api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OutputsFromInovaResponseDto {
    private int id;

    private String location;

    private String description;
}
