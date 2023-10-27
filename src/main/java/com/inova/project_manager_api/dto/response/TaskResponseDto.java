package com.inova.project_manager_api.dto.response;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data

public class TaskResponseDto {
    private int id;

    private String taskTitle;

    private String taskDescription;

    private Date date;
}
