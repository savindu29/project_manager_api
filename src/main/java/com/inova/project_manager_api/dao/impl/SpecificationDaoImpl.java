package com.inova.project_manager_api.dao.impl;

import com.inova.project_manager_api.dao.SpecificationDao;
import com.inova.project_manager_api.dto.request.ProjectImplemntationDto;
import com.inova.project_manager_api.dto.request.SpecificationRequestDto;
import com.inova.project_manager_api.dto.response.ProjectStatusSimpleResponseDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public class SpecificationDaoImpl implements SpecificationDao {
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<SpecificationRequestDto> specification() {
        try {

            String nativeQuery = "SELECT name FROM mst_specification ";
            List result  = entityManager.createNativeQuery(nativeQuery).getResultList();

            List<Object> resultList = (List<Object>) result;

            List<SpecificationRequestDto> dtos = new ArrayList<>();

            for (Object row : resultList) {
                SpecificationRequestDto dto = new SpecificationRequestDto();

                dto.setName((String) row);
                dtos.add(dto);

            }
            return dtos;
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}

