package com.inova.project_manager_api.services.impl;

import com.inova.project_manager_api.dto.response.ProjectStatusResponseDto;
import com.inova.project_manager_api.repositories.ProjectStatusRepo;
import com.inova.project_manager_api.services.ProjectStatusService;
import com.inova.project_manager_api.utils.StandardResponse;
import com.inova.project_manager_api.utils.mapper.ProjectStatusMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectStatusServiceImpl implements ProjectStatusService {

    @Autowired
    private ProjectStatusRepo projectStatusRepo;
    private final ProjectStatusMapper projectStatusMapper = new ProjectStatusMapper();


    @Override
    public StandardResponse findAllProjectStatus() {
        List<ProjectStatusResponseDto> projectStatusResponseDtos = projectStatusMapper.toProjectStatusResponseDtoList(
                projectStatusRepo.findAll()
        );
        if(projectStatusResponseDtos.size()>0){

            return new StandardResponse(
                    200,
                    "Data list",
                    projectStatusResponseDtos
            );
        }else{
            return new StandardResponse(
                    404,
                    "Data not found",
                    null
            );
        }
    }
}
