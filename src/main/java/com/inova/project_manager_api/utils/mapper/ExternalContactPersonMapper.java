package com.inova.project_manager_api.utils.mapper;

import com.inova.project_manager_api.dto.response.ExternalContactPersonResponseDto;
import com.inova.project_manager_api.dto.response.TaskResponseDto;
import com.inova.project_manager_api.dto.response.TodoRequestDtoResponseDto;
import com.inova.project_manager_api.entities.ExternalContactPerson;
import com.inova.project_manager_api.entities.Task;
import com.inova.project_manager_api.entities.Todo;

import java.util.ArrayList;
import java.util.List;

public class ExternalContactPersonMapper {
    public ExternalContactPersonResponseDto toExternalContactPersonResponseDto(ExternalContactPerson e) {

        return new ExternalContactPersonResponseDto(
                e.getId(),
                e.getName(),
                e.getMobile(),
                e.getFixTel(),
                e.getEmail(),
                e.getDesignation(),
                e.getDescription()
        );
    }
}
