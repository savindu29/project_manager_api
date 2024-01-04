package com.inova.project_manager_api.dao.impl;

import com.inova.project_manager_api.dao.ProjectResourceDao;
import com.inova.project_manager_api.dto.request.ProjectResourceDto;
import com.inova.project_manager_api.dto.response.ProjectStatusSimpleResponseDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.ArrayList;

import java.util.List;
@Repository
public class ProjectResourceDaoImpl implements ProjectResourceDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<ProjectResourceDto> ResourceList() {
        try {

            String name = "SELECT e.name, pr.allocated_date, pr.release_date, pr.approved, pr.percentage " +
                    "FROM employee e " +
                    "JOIN project_resource pr ON e.id = pr.employee_id WHERE pr.project_id=1";
            List<Object[]> results  = entityManager.createNativeQuery(name).getResultList();



            List<ProjectResourceDto> dtos = new ArrayList<>();

            for (Object[] row : results) {
                ProjectResourceDto dto = new ProjectResourceDto();

                dto.setName((String) row[0]);  // Assuming name is the first column
                dto.setAllocated_date(((Date) row[1]));  // Assuming allocated_date is the second column
                dto.setReleased_date(((Date) row[2]));   // Assuming release_date is the third column
                dto.setStatus((boolean) row[3]);
                dto.setPercentage((int) row[4]);

                dtos.add(dto);
            }
            return dtos;
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;

    }
}
