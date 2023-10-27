package com.inova.project_manager_api.dto.response;

import jakarta.persistence.Column;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StatusHistoryResponseDto {
    private int id;

    private Date date;

    private String description;
}
