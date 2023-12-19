package com.inova.project_manager_api.dto.request;

import lombok.Data;

@Data

public class ProjectStatusRequestDto {
    private String name;

    private String code;

    private int stageCode;
}