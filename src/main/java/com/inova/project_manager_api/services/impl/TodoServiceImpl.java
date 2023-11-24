package com.inova.project_manager_api.services.impl;

import com.inova.project_manager_api.dto.request.TaskRequestDto;
import com.inova.project_manager_api.dto.request.TodoRequestDto;
import com.inova.project_manager_api.entities.Project;
import com.inova.project_manager_api.entities.Task;
import com.inova.project_manager_api.entities.Todo;
import com.inova.project_manager_api.repositories.ProjectRepo;
import com.inova.project_manager_api.repositories.TaskRepo;
import com.inova.project_manager_api.repositories.TodoRepo;
import com.inova.project_manager_api.services.TodoService;
import com.inova.project_manager_api.utils.StandardResponse;
import com.inova.project_manager_api.utils.mapper.TaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TodoServiceImpl implements TodoService {
    @Autowired
    private ProjectRepo projectRepo;
    @Autowired
    private TodoRepo todoRepo;
    @Autowired
    private TaskRepo taskRepo;
    private final TaskMapper taskMapper = new TaskMapper();
    @Override
    public ResponseEntity<StandardResponse> updateTodo(TodoRequestDto dto, int projectId) {
        try{
            Optional<Project> byId = projectRepo.findById(projectId);
            if (byId.isEmpty()) {
                return new ResponseEntity<>(
                        new StandardResponse(
                                404,
                                "there is no project with id = "+projectId,
                                null
                        ),
                        HttpStatus.INTERNAL_SERVER_ERROR
                );
            } else {
                Project  project = byId.get();
                int todoId = project.getTodo().getId();
                Todo todo = todoRepo.findById(todoId).get();
                todo.setNotes(dto.getNotes() !=null ? dto.getNotes() : todo.getNotes());
                Todo save = todoRepo.save(todo);

                if(dto.getTasks()!=null || dto.getTasks().size()>0){
                    for (TaskRequestDto request: dto.getTasks()) {

                        if(request.getId()!=-1){
                            Optional<Task> taskRes = taskRepo.findById(request.getId());
                            Task t = taskRes.get();
                            t.setTaskTitle(request.getTaskTitle());
                            t.setDate(request.getDate());
                            t.setTaskDescription(request.getTaskDescription());
                            t.setDone(request.isDone());
                            taskRepo.save(t);
                        }else{
                            Task task = taskMapper.toTaskEntity(request);
                            task.setTodo(save);
                            taskRepo.save(task);
                        }

                    }
                }



                return new ResponseEntity<>(
                        new StandardResponse(
                                200,
                                "updated todos with project id : "+projectId,
                                null
                        ),
                        HttpStatus.OK
                );

            }


        }catch (Exception e){
            return new ResponseEntity<>(
                    new StandardResponse(
                            500,
                            "Error occurred while saving the project: " + e.getMessage(),
                            null
                    ),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}
