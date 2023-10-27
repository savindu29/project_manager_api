package com.inova.project_manager_api.dto.response;

import jakarta.persistence.Column;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RfpResourceResponseDto {
    private int id;

    private String location;

    private String description;
}
