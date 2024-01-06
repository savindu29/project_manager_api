package com.inova.project_manager_api.controllers;

import com.inova.project_manager_api.services.ProjectResourcesService;
import com.inova.project_manager_api.utils.StandardResponse;

import jakarta.mail.Message;
import jakarta.servlet.http.HttpServletResponse;

import java.sql.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/projectReosurces")
@CrossOrigin(origins = "http://localhost:3000")
public class ProjectResourcesController {
	
	private static final  Logger logger = LoggerFactory.getLogger(ProjectResourceController.class);
	
    @Autowired
    ProjectResourcesService projectResourcesService;
     
    @GetMapping(value = "/ResourceList")
    public ResponseEntity<StandardResponse> ResourceList(
    		@RequestParam(value = "projectId" , required = false) Integer projectId,
    		@RequestParam(value = "allocatedDate" , required = false) Date allocatedDate,
    		@RequestParam(value = "releasedDate" , required = false) Date releasedDate,
    		@RequestParam(value = "employeeName" , required = false )String employeeName,HttpServletResponse response) {
    	
    	StandardResponse standardResponse = new StandardResponse();
    	
    	try {    		
    		standardResponse = projectResourcesService.resources(projectId , allocatedDate ,releasedDate , employeeName);
    		
    	}catch(Exception e) {
    		logger.error("Get Request List failed due to: {} {}" , e.getMessage() , e);
    		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(standardResponse);    		
    	}
    	
    	logger.info("Get Request list is Completed. {} ",standardResponse);
		return ResponseEntity.status(HttpStatus.OK).body(standardResponse); 
    }
    
    
}
