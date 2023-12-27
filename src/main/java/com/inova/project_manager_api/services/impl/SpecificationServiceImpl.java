package com.inova.project_manager_api.services.impl;

import com.inova.project_manager_api.dao.SpecificationDao;
import com.inova.project_manager_api.dto.request.ProjectImplemntationDto;
import com.inova.project_manager_api.dto.request.SpecificationRequestDto;
import com.inova.project_manager_api.dto.response.ProjectStatusSimpleResponseDto;
import com.inova.project_manager_api.services.SpecificationService;
import com.inova.project_manager_api.utils.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SpecificationServiceImpl implements SpecificationService {
    @Autowired
    private SpecificationDao SpecificationDao;
    @Override
    public StandardResponse specification() {
        try{
            List<SpecificationRequestDto> names = SpecificationDao.specification();
            return
                    new StandardResponse(
                            200,
                            "sucess " ,
                            names
                    );

        }catch (Throwable e) {

            return new StandardResponse(
                    500,
                    "Error occurred while saving the project: " + e.getMessage(),
                    null
            );

        }
    }
}
