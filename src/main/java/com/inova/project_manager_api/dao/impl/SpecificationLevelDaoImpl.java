package com.inova.project_manager_api.dao.impl;

import com.inova.project_manager_api.dao.SpecificationLevelDao;
import com.inova.project_manager_api.dto.request.SpecificationLevelRequestDto;
import com.inova.project_manager_api.dto.request.SpecificationRequestDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class SpecificationLevelDaoImpl implements SpecificationLevelDao {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<SpecificationLevelRequestDto> specificationLevel() {
        try {

            String nativeQuery = "SELECT name FROM mst_specification_level ";
            List result  = entityManager.createNativeQuery(nativeQuery).getResultList();

            List<Object> resultList = (List<Object>) result;

            List<SpecificationLevelRequestDto> dtos = new ArrayList<>();

            for (Object row : resultList) {
                SpecificationLevelRequestDto dto = new SpecificationLevelRequestDto();

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

