package com.inova.project_manager_api.services.impl;

import com.inova.project_manager_api.dao.ProjectDao;
import com.inova.project_manager_api.dao.ProjectResourceDao;
import com.inova.project_manager_api.dto.request.ProjectResourceDto;
import com.inova.project_manager_api.dto.response.ProjectStatusSimpleResponseDto;
import com.inova.project_manager_api.repositories.ProjectRepo;
import com.inova.project_manager_api.services.ProjectResourcesService;
import com.inova.project_manager_api.utils.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class ProjectResourcesServiceImpl implements ProjectResourcesService {

    @Autowired
    private ProjectResourceDao projectResourceDao;

	@Override
	public StandardResponse resources(Integer projectId, Date allocatedDate, Date releasedDate, String employeeName) {
		try{
			List<ProjectResourceDto> ResourceList = projectResourceDao.ResourceList(projectId ,allocatedDate , releasedDate , employeeName);
			return new StandardResponse( 200, "sucess " ,ResourceList);
			
		}catch (Throwable e) {
			return new StandardResponse(500, "Error occurred while saving the project: " + e.getMessage(),null );
			}
		}
	}
