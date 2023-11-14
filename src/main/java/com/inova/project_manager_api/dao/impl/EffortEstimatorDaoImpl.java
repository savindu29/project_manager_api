package com.inova.project_manager_api.dao.impl;

import com.inova.project_manager_api.dao.EffortEstimatorDao;
import com.inova.project_manager_api.dto.response.ProjectSimpleResponseDto;
import com.inova.project_manager_api.dto.response.ResponsiblePersonInovaResponseDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EffortEstimatorDaoImpl implements EffortEstimatorDao {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public boolean save(int projectId, int employeeId) {
        String nativeQuery = "INSERT into project_effort_estimators (project_id,effort_estimator_id) values (:projectId , :employeeId)";
        List<Object[]> resultList = entityManager.createNativeQuery(nativeQuery)
                .setParameter("projectId",projectId)
                .setParameter("employeeId",employeeId)
                .getResultList();


        return true;
    }

    @Override
    public List<ResponsiblePersonInovaResponseDto> getEffortEstimatorsByProject(int projectId) {
        List<ResponsiblePersonInovaResponseDto> dtos = new ArrayList<>();
        String nativeQuery = "select * from project_effort_estimators where project_id = :projectId";
        List<Object[]> resultList = entityManager.createNativeQuery(nativeQuery)
                .setParameter("projectId",projectId)
                .getResultList();

        for (Object[] row : resultList) {
//                String csquery = "SELECT date FROM status_history AS sh WHERE project_id = 1 ORDER BY sh.date DESC LIMIT 1;";
            ResponsiblePersonInovaResponseDto dto = new ResponsiblePersonInovaResponseDto();
            dto.setId((Integer) row[0]);
            dto.setName((String) row[1]);
            dto.setMobile((String) row[2]);
            dto.setCompanyEmail((String) row[3]);
            dto.setPrivateEmail((String) row[4]);
            dto.setDesignation((String) row[5]);
            dto.setSpecializedField((String) row[5]);
            dtos.add(dto);
        }
        if(resultList.size()==0){
            return null;
        }
        return dtos;




    }
}
