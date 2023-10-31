package com.inova.project_manager_api.services;


import com.inova.project_manager_api.dto.AppRequest;
import com.inova.project_manager_api.dto.request.ProjectDetailsSubmitRequestDto;
import com.inova.project_manager_api.dto.response.ProjectDetailsSubmitResponseDto;
import com.inova.project_manager_api.exceptions.ApplicationGeneralException;
import org.springframework.http.ResponseEntity;
import com.inova.project_manager_api.dto.paginatedData.PaginatedProjectData;
import com.inova.project_manager_api.dto.request.ProjectRequestDto;
import com.inova.project_manager_api.dto.response.ProjectAdvanceResponseDto;

public interface ProjectService {

//    ProjectResponseDto findProject(int id);

    ResponseEntity<ProjectDetailsSubmitResponseDto> projectDetailsSubmit(AppRequest<ProjectDetailsSubmitRequestDto> request) throws ApplicationGeneralException;

    ProjectAdvanceResponseDto findProject(int id);
    PaginatedProjectData findAllProjects(int page, int count);
    String updateProject(ProjectRequestDto dto, String id);

}
