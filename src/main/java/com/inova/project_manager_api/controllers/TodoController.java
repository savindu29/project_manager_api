package com.inova.project_manager_api.controllers;

import com.inova.project_manager_api.dto.request.TodoRequestDto;
import com.inova.project_manager_api.services.TodoService;
import com.inova.project_manager_api.utils.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/todo")
@CrossOrigin(origins = "http://localhost:3000")
public class TodoController {
    @Autowired
    private TodoService todoService;
    @PutMapping(value = "/update", params = {"projectId"})
    public ResponseEntity<StandardResponse> updateTodos(@RequestBody TodoRequestDto dto , @RequestParam String projectId )  {

        try {
            System.out.println(projectId);
            int pId = Integer.parseInt(projectId);
            ResponseEntity<StandardResponse> todo = todoService.updateTodo(dto,pId);
            return todo;
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(
                    new StandardResponse(
                            400,
                            "Error",
                            null
                    ),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }

    }
}
