package com.inova.project_manager_api.services;

import com.inova.project_manager_api.dto.request.StatusHistoryRequestDto;
import com.inova.project_manager_api.utils.StandardResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface StatusHistoryService {
    ResponseEntity<StandardResponse>  getStatusHistoryOrderByDate(int projectId);

    ResponseEntity<StandardResponse> updateStatusHistory(List<StatusHistoryRequestDto> dtos, int pId);
}
