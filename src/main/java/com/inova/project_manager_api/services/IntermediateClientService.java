package com.inova.project_manager_api.services;

import com.inova.project_manager_api.dto.request.IntermediateClientRequestDto;
import com.inova.project_manager_api.utils.StandardResponse;
import org.springframework.http.ResponseEntity;

public interface IntermediateClientService {
    ResponseEntity<StandardResponse> updateIntermediateClient(IntermediateClientRequestDto dto, int id);

    ResponseEntity<StandardResponse> getIntermediateClient(int id);
}
