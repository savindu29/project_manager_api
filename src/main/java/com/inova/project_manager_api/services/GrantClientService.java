package com.inova.project_manager_api.services;

import com.inova.project_manager_api.dto.request.GrantClientRequestDto;
import com.inova.project_manager_api.utils.StandardResponse;
import org.springframework.http.ResponseEntity;

public interface GrantClientService {
    ResponseEntity<StandardResponse> updateGrantClient(GrantClientRequestDto dto, int id);

    ResponseEntity<StandardResponse> getGrantClient(int id);
}
