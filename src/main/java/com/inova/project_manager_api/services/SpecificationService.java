package com.inova.project_manager_api.services;


import com.inova.project_manager_api.utils.StandardResponse;


public interface SpecificationService {

    StandardResponse getSpecificationsByArea(String area);
    StandardResponse getAllSpecificationAreas();
}
