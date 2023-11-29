package com.inova.project_manager_api.dto.request;

import lombok.Data;

@Data
public class ProjectImplemntationDto {
    private int ImplenetaionSucess;
    private int ImplementationInProgress;
    private int ImplementationFailed;
}
