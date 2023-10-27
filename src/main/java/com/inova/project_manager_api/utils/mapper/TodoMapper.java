package com.inova.project_manager_api.utils.mapper;

import com.inova.project_manager_api.dto.response.TaskResponseDto;
import com.inova.project_manager_api.dto.response.TodoRequestDtoResponseDto;
import com.inova.project_manager_api.entities.Task;
import com.inova.project_manager_api.entities.Todo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class TodoMapper {
    @Autowired
    private TaskMapper taskMapper;

    public TodoRequestDtoResponseDto toTodoRequestDtoResponseDto(Todo t) {

        return new TodoRequestDtoResponseDto(
                t.getId(),
                t.getNotes(),
                taskMapper.toTaskResponseDtoList(t.getTasks())
        );
    }
}
