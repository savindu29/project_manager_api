package com.inova.project_manager_api.dto.request;

import com.inova.project_manager_api.entities.ProjectRole;
import lombok.Data;

import java.sql.Date;
import java.util.List;

@Data
public class SendResourceRequestDto {
    private List<ProjectResourceRequestDto> resourceSpec;


    private int userRole;
    private String note;
}
