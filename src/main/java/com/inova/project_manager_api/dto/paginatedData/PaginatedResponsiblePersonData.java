package com.inova.project_manager_api.dto.paginatedData;

import com.inova.project_manager_api.dto.response.ProjectSimpleResponseDto;
import com.inova.project_manager_api.dto.response.ResponsiblePersonInovaResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaginatedResponsiblePersonData {
    private int count;
    private List<ResponsiblePersonInovaResponseDto> data;
}
