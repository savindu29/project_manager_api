package com.inova.project_manager_api.services.impl;

import com.inova.project_manager_api.dao.SpecificationDao;
import com.inova.project_manager_api.dao.SpecificationLevelDao;
import com.inova.project_manager_api.dto.request.SpecificationLevelRequestDto;
import com.inova.project_manager_api.dto.request.SpecificationRequestDto;
import com.inova.project_manager_api.services.SpecificationLevelService;
import com.inova.project_manager_api.utils.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecificationLevelServiceImpl implements SpecificationLevelService {
    @Autowired
    private SpecificationLevelDao SpecificationLevelDao;



    @Override
    public StandardResponse specificationLevel() {
        try{
            List<SpecificationLevelRequestDto> names = SpecificationLevelDao.specificationLevel();
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

