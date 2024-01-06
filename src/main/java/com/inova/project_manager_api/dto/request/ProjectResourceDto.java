package com.inova.project_manager_api.dto.request;

import lombok.Data;

import java.util.Date;

@Data
public class ProjectResourceDto {
	
    private int employeeId;
    private String employeeName;
    private Date allocatedDate;
    private Date releasedDate;

}
