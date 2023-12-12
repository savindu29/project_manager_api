package com.inova.project_manager_api.dto.request;

import lombok.Data;

@Data
public class ProjectStatsDto {
    private int propOnGoingCount;
    private int propLostCount;
    private int propWonCount;
}
