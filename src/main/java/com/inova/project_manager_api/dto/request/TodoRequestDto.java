package com.inova.project_manager_api.dto.request;

import lombok.Data;

import java.util.List;

@Data

public class TodoRequestDto {
    private String notes;
    private List<TaskRequestDto> tasks;
}
