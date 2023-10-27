package com.inova.project_manager_api.dto.response;

import lombok.Data;

import java.util.Date;


@Data
public class ProjectSimpleResponseDto {
    private int id;
    private String projectName;
    private String code;
    private String priority;
    private String todo;
    private String currentStatus;
    private Date nextTaskDate;
}
