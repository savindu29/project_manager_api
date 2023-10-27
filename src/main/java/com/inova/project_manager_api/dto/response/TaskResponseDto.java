package com.inova.project_manager_api.dto.response;

import jakarta.persistence.Column;
import lombok.*;

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
}
