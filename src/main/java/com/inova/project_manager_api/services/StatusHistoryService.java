package com.inova.project_manager_api.services;

import com.inova.project_manager_api.utils.StandardResponse;
import org.springframework.http.ResponseEntity;

public interface StatusHistoryService {
    ResponseEntity<StandardResponse>  getStatusHistoryOrderByDate(int projectId);

}
