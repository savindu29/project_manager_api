package com.inova.project_manager_api.dao;

import com.inova.project_manager_api.dto.response.ResponsiblePersonInovaResponseDto;

import java.util.List;

public interface EffortEstimatorDao {
    boolean save(int projectId,int employeeId);
    List<ResponsiblePersonInovaResponseDto> getEffortEstimatorsByProject(int projectId);
}
