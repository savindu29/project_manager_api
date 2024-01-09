package com.inova.project_manager_api.services;


import com.inova.project_manager_api.dto.request.ResourceRequestDto;
import com.inova.project_manager_api.dto.request.SendResourceRequestDto;
import com.inova.project_manager_api.dto.response.ProjectResourceResponseDto;
import com.inova.project_manager_api.dto.response.ResourceAllocationResponseDto;
import com.inova.project_manager_api.utils.StandardResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProjectResourceService {

    List<ProjectResourceResponseDto> availablePercentage(ResourceRequestDto request);

    List<ResourceAllocationResponseDto> projectAllocation(ResourceRequestDto request);

    StandardResponse getProjectsByResource(int employeeId);

    ResponseEntity<StandardResponse> sendResourceRequest(SendResourceRequestDto resourceRequest, int projectId, int employeeId);
}