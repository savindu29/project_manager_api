package com.inova.project_manager_api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EmployeeSpecificationResponseDto {
    private int id;
    private String name;
    private String specification;
    private String level;

}
