package com.inova.project_manager_api.utils.mapper;

import com.inova.project_manager_api.dto.request.TodoRequestDto;
import com.inova.project_manager_api.dto.response.TodoResponseDto;
import com.inova.project_manager_api.entities.Todo;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TodoMapper {

    private final TaskMapper taskMapper = new TaskMapper();

    public TodoResponseDto toTodoRequestDtoResponseDto(Todo t) {
        if(t==null){
            return null;
        }

        return new TodoResponseDto(
                t.getId(),
                t.getNotes(),
                taskMapper.toTaskResponseDtoList(t.getTasks())
        );
    }

    public Todo todoEntity(TodoRequestDto dto ){
        Todo t = new Todo();
        t.setNotes(dto.getNotes());
        return t;
    }
}
