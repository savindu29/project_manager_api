package com.inova.project_manager_api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data

public class StatusHistoryRequestDto {
    private int id;
    private Date date;

    private String description;
}
