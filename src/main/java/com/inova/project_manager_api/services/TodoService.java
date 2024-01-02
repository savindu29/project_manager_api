package com.inova.project_manager_api.services;

import com.inova.project_manager_api.dto.request.TodoRequestDto;
import com.inova.project_manager_api.utils.StandardResponse;
import org.springframework.http.ResponseEntity;

public interface TodoService {
    ResponseEntity<StandardResponse> updateTodo(TodoRequestDto dto, int projectId);
}
