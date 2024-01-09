package com.inova.project_manager_api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectResourceResponseDto {
    private Date fromDate;
    private Date toDate;
    private Integer percentage;

}
