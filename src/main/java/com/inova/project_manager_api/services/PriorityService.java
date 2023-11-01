package com.inova.project_manager_api.services;

import com.inova.project_manager_api.dto.response.PriorityResponseDto;

import java.util.List;

public interface PriorityService {
    List<PriorityResponseDto> findAllPriority();
}
