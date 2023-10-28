package com.inova.project_manager_api.utils.mapper;

import com.inova.project_manager_api.dto.response.RfpResourceResponseDto;
import com.inova.project_manager_api.dto.response.TaskResponseDto;
import com.inova.project_manager_api.entities.RfpResource;
import com.inova.project_manager_api.entities.Task;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@NoArgsConstructor
public class TaskMapper {
    public TaskResponseDto toTaskResponseDto(Task t) {
        return new TaskResponseDto(
                t.getId(),t.getTaskTitle(),t.getTaskDescription(),t.getDate()
        );
    }

    public List<TaskResponseDto> toTaskResponseDtoList(List<Task> list) {
        List<TaskResponseDto> dtoList = new ArrayList<>();
        for (Task t: list) {
            dtoList.add(toTaskResponseDto(t));
        }
        return dtoList;
    }
}
