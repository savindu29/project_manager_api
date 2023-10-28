package com.inova.project_manager_api.utils.mapper;

import com.inova.project_manager_api.dto.response.TaskResponseDto;
import com.inova.project_manager_api.dto.response.TodoRequestDtoResponseDto;
import com.inova.project_manager_api.entities.Task;
import com.inova.project_manager_api.entities.Todo;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
@NoArgsConstructor
public class TodoMapper {

    private final TaskMapper taskMapper = new TaskMapper();

    public TodoRequestDtoResponseDto toTodoRequestDtoResponseDto(Todo t) {

        return new TodoRequestDtoResponseDto(
                t.getId(),
                t.getNotes(),
                taskMapper.toTaskResponseDtoList(t.getTasks())
        );
    }
}
