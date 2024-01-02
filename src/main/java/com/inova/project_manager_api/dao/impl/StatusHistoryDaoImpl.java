package com.inova.project_manager_api.dao.impl;

import com.inova.project_manager_api.dao.StatusHistoryDao;
import com.inova.project_manager_api.dto.response.StatusHistoryResponseDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Repository
public class StatusHistoryDaoImpl implements StatusHistoryDao {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<StatusHistoryResponseDto> getStatusHistoryOrderByDate(int projectId) {
        try {
            String dateQuery = "SELECT * FROM status_history  WHERE project_id = :projectId ORDER BY date DESC";
            List<Object[]> resultList = entityManager.createNativeQuery(dateQuery)
                    .setParameter("projectId", projectId).getResultList();


            List<StatusHistoryResponseDto> dtos = new ArrayList<>();

            for (Object[] row : resultList) {
                StatusHistoryResponseDto dto = new StatusHistoryResponseDto();
                dto.setId((Integer) row[0]);
                dto.setDate((Date) row[1]);
                dto.setDescription((String) row[2]);
                dtos.add(dto);
            }
            return dtos;
        } catch (Exception ex) {
            return null;
        }
    }
}
