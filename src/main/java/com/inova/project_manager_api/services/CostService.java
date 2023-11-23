package com.inova.project_manager_api.services;

import com.inova.project_manager_api.dto.request.CostRequestDto;
import com.inova.project_manager_api.dto.request.ProjectRequestDto;
import com.inova.project_manager_api.utils.StandardResponse;
import org.springframework.http.ResponseEntity;

public interface CostService {
    ResponseEntity<StandardResponse> updateCost(CostRequestDto dto, int projectId);
}
