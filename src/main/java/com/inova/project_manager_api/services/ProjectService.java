package com.inova.project_manager_api.services;


import com.inova.project_manager_api.dto.AppRequest;
import com.inova.project_manager_api.dto.request.ProjectDetailsSubmitRequestDto;
import com.inova.project_manager_api.dto.request.ProjectRequestDto;
import com.inova.project_manager_api.dto.request.ProjectUpdateRequestDto;
import com.inova.project_manager_api.dto.response.ImageUploadResponseDto;
import com.inova.project_manager_api.dto.response.ProjectDetailsSubmitResponseDto;
import com.inova.project_manager_api.exceptions.ApplicationGeneralException;
import com.inova.project_manager_api.utils.StandardResponse;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface ProjectService {

//    ProjectResponseDto findProject(int id);

    ResponseEntity<ProjectDetailsSubmitResponseDto> projectDetailsSubmit(AppRequest<ProjectDetailsSubmitRequestDto> request) throws ApplicationGeneralException;

    StandardResponse findProject(int id);
    StandardResponse findAllProjects(int page, int count,String searchtext);

    @Transactional(rollbackOn = Throwable.class)
    ResponseEntity<StandardResponse> updateProject(ProjectUpdateRequestDto request, int id);

    StandardResponse deleteProject(int intId);

    ResponseEntity<StandardResponse> createProject(ProjectRequestDto request);

//    ResponseEntity<ImageUploadResponseDto> uploadImage(Integer projectId, String imageString, String description) throws ApplicationGeneralException, IOException;
}
