package com.inova.project_manager_api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TaskResponseDto {
    private int id;

    private String taskTitle;

    private String taskDescription;


    private Date date;
    private boolean isDone;
}
