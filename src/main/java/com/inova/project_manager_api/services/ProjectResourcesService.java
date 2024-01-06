package com.inova.project_manager_api.services;

import java.sql.Date;

import com.inova.project_manager_api.utils.StandardResponse;

public interface ProjectResourcesService {
	
    StandardResponse resources(Integer projectId, Date allocatedDate, Date releasedDate, String employeeName);
}
