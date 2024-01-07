package com.inova.project_manager_api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResourceSpecificationRequestDto {
    private List<Integer> specifications;
    private List<Integer> specificationLevels;
}
