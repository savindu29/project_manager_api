package com.inova.project_manager_api.dao.impl;

import com.inova.project_manager_api.dao.ProjectDao;
import com.inova.project_manager_api.dto.response.ProjectSimpleResponseDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class ProjectDaoImpl implements ProjectDao {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<ProjectSimpleResponseDto> getAllProjects( int page, int size) {
        StringBuilder nativeQuery = new StringBuilder();
        nativeQuery.append("SELECT pj.id, pj.name, pj.code, pr.name AS priority_name, td.notes, st.name AS status_name FROM project AS pj  JOIN priority AS pr ON pj.priority = pr.id " +
                "JOIN todo AS td ON pj.todo_id = td.id JOIN project_status AS st ON st.id = pj.project_status;");





        try {
            System.out.println(nativeQuery.toString());
            String queryStr = nativeQuery.toString();
            List<Object[]> resultList = entityManager.createNativeQuery(queryStr).getResultList();

            List<ProjectSimpleResponseDto> dtos = new ArrayList<>();

            for (Object[] row : resultList) {
                String csquery = "SELECT date FROM status_history AS sh WHERE project_id = 1 ORDER BY sh.date DESC LIMIT 1;";
                ProjectSimpleResponseDto dto = new ProjectSimpleResponseDto();
                dto.setId((Integer) row[0]);
                dto.setProjectName((String) row[1]);
                dto.setCode((String) row[2]);
                dto.setPriority((String) row[3]);
                dto.setTodo((String) row[4]);
                dto.setCurrentStatus((String) row[5]);
                String dateQuery = "SELECT sh.date FROM status_history AS sh WHERE project_id = :projectId ORDER BY sh.date DESC LIMIT 1";
                Date date = (Date) entityManager.createNativeQuery(dateQuery)
                        .setParameter("projectId", row[0])
                        .getSingleResult();

                dto.setLatestStatusHistoryDate(date);
                // Set other fields as needed
                dtos.add(dto);
            }
            return dtos;
        }catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;


    }
}
