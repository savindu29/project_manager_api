package com.inova.project_manager_api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectResourceTableResponseDto {
    private Date fromDate;
    private Date toDate;
    private String project;
    private boolean approved;

}
