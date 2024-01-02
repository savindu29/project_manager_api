package com.inova.project_manager_api.controllers;

import com.inova.project_manager_api.services.ResourceService;
import com.inova.project_manager_api.utils.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


public class ResourceController {
    @Autowired
    private ResourceService resourceService ;

    public ResponseEntity<StandardResponse> searchPotentialResources()

    {
        return new ResponseEntity<>(

                resourceService. searchPotentialResources (),
                HttpStatus.OK
        );
    }

}
