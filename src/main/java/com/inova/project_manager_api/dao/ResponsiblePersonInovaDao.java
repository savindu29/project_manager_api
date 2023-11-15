package com.inova.project_manager_api.dao;

import com.inova.project_manager_api.dto.response.ProjectSimpleResponseDto;
import com.inova.project_manager_api.dto.response.ResponsiblePersonInovaResponseDto;

import java.util.List;

public interface ResponsiblePersonInovaDao {
    List<ResponsiblePersonInovaResponseDto> searchAllProjects(int page, int size,String searchtext);
    int getProjectCount();

    List<ResponsiblePersonInovaResponseDto> searchEmployeeByname(String searchtext);
}
