package com.inova.project_manager_api.dto.request;

import com.inova.project_manager_api.dto.response.TaskResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data

public class TodoRequestDto {
    private String notes;
    private List<TaskRequestDto> tasks;
}
