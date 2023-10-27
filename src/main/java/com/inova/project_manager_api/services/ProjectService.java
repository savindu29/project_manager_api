package com.inova.project_manager_api.services;

import com.inova.project_manager_api.dto.response.ProjectAdvanceResponseDto;

import org.springframework.stereotype.Service;

import java.util.List;
public interface ProjectService {
    ProjectAdvanceResponseDto findProject(int id);
}
