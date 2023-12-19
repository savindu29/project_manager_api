package com.inova.project_manager_api.dto.paginatedData;

import com.inova.project_manager_api.dto.response.ProjectSimpleResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaginatedProjectData {
    private int count;
    private List<ProjectSimpleResponseDto> data;
}
