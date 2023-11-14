package com.inova.project_manager_api.dao;

import com.inova.project_manager_api.dto.response.ProjectSimpleResponseDto;

import java.util.List;

public interface ProjectDao {
    List<ProjectSimpleResponseDto> getAllProjects(int page,int size,String searchtext);
    int getProjectCount();
}
