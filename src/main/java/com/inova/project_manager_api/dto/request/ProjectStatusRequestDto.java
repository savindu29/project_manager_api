package com.inova.project_manager_api.dto.request;
import jakarta.persistence.*;
import lombok.*;

@Data

public class ProjectStatusRequestDto {
    private String name;

    private String code;

    private int stageCode;
}