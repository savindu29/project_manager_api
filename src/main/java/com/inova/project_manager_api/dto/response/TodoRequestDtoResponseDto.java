package com.inova.project_manager_api.dto.response;

import com.inova.project_manager_api.entities.Task;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TodoRequestDtoResponseDto {
    private int id;

    private String notes;

    private List<TaskResponseDto> tasks;
}
