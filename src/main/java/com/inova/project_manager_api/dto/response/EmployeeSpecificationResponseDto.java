package com.inova.project_manager_api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EmployeeSpecificationResponseDto {
    private int id;
    private String name;
    private List<SkillResponseDto> skills;


}
