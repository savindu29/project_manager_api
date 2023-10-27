package com.inova.project_manager_api.services;

import com.inova.project_manager_api.dto.response.ProjectResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;
public interface ProjectService {
    ProjectResponseDto findProject(int id);
}
