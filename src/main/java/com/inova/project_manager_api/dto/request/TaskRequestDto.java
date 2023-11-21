package com.inova.project_manager_api.dto.request;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data

public class TaskRequestDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String taskTitle;

    private String taskDescription;

    private Date date;

    // Add a setter for id
    public void setId(Long id) {
        this.id = id;
    }
}
