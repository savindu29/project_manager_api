package com.inova.project_manager_api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data

public class TaskRequestDto {
    private String taskTitle;

    private String taskDescription;

    private Date date;
    private boolean isDone;
}
