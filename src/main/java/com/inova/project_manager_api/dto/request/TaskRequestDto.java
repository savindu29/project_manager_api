package com.inova.project_manager_api.dto.request;

import lombok.Data;

import java.sql.Date;

@Data

public class TaskRequestDto {
    private int id;
    private String taskTitle;

    private String taskDescription;

    private Date date;
    private boolean isDone;
}
