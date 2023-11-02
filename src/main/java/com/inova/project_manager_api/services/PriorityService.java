package com.inova.project_manager_api.services;

import com.inova.project_manager_api.dto.response.PriorityResponseDto;
import com.inova.project_manager_api.utils.StandardResponse;

import java.util.List;

public interface PriorityService {
    StandardResponse findAllPriority();
}
