package com.inova.project_manager_api.utils.mapper;

import com.inova.project_manager_api.dto.request.TaskRequestDto;
import com.inova.project_manager_api.dto.response.TaskResponseDto;
import com.inova.project_manager_api.entities.Task;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class TaskMapper {
    public TaskResponseDto toTaskResponseDto(Task t) {
        if(t==null){
            return null;
        }
        return new TaskResponseDto(
                t.getId(), t.getTaskTitle(), t.getTaskDescription(), t.getDate()
        );
    }

    public List<TaskResponseDto> toTaskResponseDtoList(List<Task> list) {
        if(list==null){
            return null;
        }
        List<TaskResponseDto> dtoList = new ArrayList<>();
        for (Task t : list) {
            dtoList.add(toTaskResponseDto(t));
        }
        return dtoList;
    }
    public Task toTaskEntity(TaskRequestDto dto) {
        Task t = new Task();
        t.setTaskTitle(dto.getTaskTitle());
        t.setTaskDescription(dto.getTaskDescription());
        t.setDate(dto.getDate());
        return t;
    }

    public List<Task> toTaskEntityList(List<TaskRequestDto> list) {
        List<Task> entityListList = new ArrayList<>();
        for (TaskRequestDto t : list) {
            entityListList.add(toTaskEntity(t));
        }
        return entityListList;
    }

}
