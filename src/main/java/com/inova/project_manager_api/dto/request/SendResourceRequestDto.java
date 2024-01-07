package com.inova.project_manager_api.dto.request;

import com.inova.project_manager_api.entities.ProjectRole;
import lombok.Data;

import java.sql.Date;
@Data
public class SendResourceRequestDto {
    private Date allocateDate;
    private Date releaseDate;
    private int percentage;
    private ProjectRole userRole;
    private String note;
}
