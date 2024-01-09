package com.inova.project_manager_api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class ResourceRequestDto {
    private int empId;

    private String fromDate;

    private String toDate;

}
