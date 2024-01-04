package com.inova.project_manager_api.dto.request;

import lombok.Data;

import java.util.Date;

@Data
public class ProjectResourceDto {
    private String name;
    private boolean status;
    private Date allocated_date;
    private Date released_date;
    private int percentage;

}
