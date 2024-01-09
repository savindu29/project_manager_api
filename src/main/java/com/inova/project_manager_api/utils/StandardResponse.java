package com.inova.project_manager_api.utils;

import com.inova.project_manager_api.dto.request.EmployeeNameDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StandardResponse {
    private int code; // HTTP response code
    private String message;
    private Object data;

    public StandardResponse(String message, List<EmployeeNameDto> employeeNames) {
        this.code = 200;
        this.message = message;
        this.data = employeeNames;
    }
}
