package com.inova.project_manager_api.services;

import com.inova.project_manager_api.dto.AppRequest;
import com.inova.project_manager_api.dto.AppResponse;
import com.inova.project_manager_api.dto.request.ProjectDetailsSubmitRequestDto;
import com.inova.project_manager_api.dto.response.ProjectDetailsSubmitResponseDto;
import com.inova.project_manager_api.dto.response.ProjectResponseDto;
import com.inova.project_manager_api.exceptions.ApplicationGeneralException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
public interface ProjectService {
    ProjectResponseDto findProject(int id);

    ResponseEntity<AppResponse<ProjectDetailsSubmitResponseDto>> projectDetailsSubmit(AppRequest<ProjectDetailsSubmitRequestDto> request) throws ApplicationGeneralException;
}
