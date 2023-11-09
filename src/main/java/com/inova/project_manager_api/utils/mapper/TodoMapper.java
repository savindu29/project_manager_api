package com.inova.project_manager_api.utils.mapper;

import com.inova.project_manager_api.dto.response.TodoResponseDto;
import com.inova.project_manager_api.entities.Todo;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TodoMapper {

    private final TaskMapper taskMapper = new TaskMapper();

    public TodoResponseDto toTodoRequestDtoResponseDto(Todo t) {

        return new TodoResponseDto(
                t.getId(),
                t.getNotes(),
                taskMapper.toTaskResponseDtoList(t.getTasks())
        );
    }
}
