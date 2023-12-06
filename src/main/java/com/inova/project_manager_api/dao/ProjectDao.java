package com.inova.project_manager_api.dao;

import com.inova.project_manager_api.dto.request.ProjectImplemntationDto;
import com.inova.project_manager_api.dto.request.ProjectStatsDto;
import com.inova.project_manager_api.dto.response.ProjectSimpleResponseDto;
import com.inova.project_manager_api.dto.response.ProjectStatusSimpleResponseDto;

import java.util.List;

public interface ProjectDao {
    List<ProjectSimpleResponseDto> getAllProjects(int page,int size,String searchtext);

    int getProjectCount(String searchText);

    ProjectStatsDto getProposalStat();


    ProjectImplemntationDto getImplementationStat();

    List<ProjectStatusSimpleResponseDto> getWonProposalName();

    List<ProjectStatusSimpleResponseDto> getInProgressProposalName();

    List<ProjectStatusSimpleResponseDto> LossProposalName();

    List<ProjectStatusSimpleResponseDto> getInprogressProjectNames();

    List<ProjectStatusSimpleResponseDto> getImplFailedProjectNames();

    List<ProjectStatusSimpleResponseDto> getImplSucessProjectNames();
}
