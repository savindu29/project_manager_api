package com.inova.project_manager_api.dto.response;

import com.inova.project_manager_api.entities.Task;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data

public class TodoRequestDtoResponseDto {
    private int id;

    private String notes;

    private List<TaskResponseDto> tasks;
}
