package com.inova.project_manager_api.dao.impl;

import com.inova.project_manager_api.dao.ResponsiblePersonInovaDao;
import com.inova.project_manager_api.dto.response.ResponsiblePersonInovaResponseDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ResponsiblePersonInovaDaoImpl implements ResponsiblePersonInovaDao {

    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<ResponsiblePersonInovaResponseDto> searchAllProjects(int page, int count, String searchtext) {
        int offset = (page - 1) * count; // Calculate the offset based on the current page and page size

        String nativeQuery = "SELECT * FROM responsible_person_inova WHERE name LIKE :searchtext LIMIT :limit OFFSET :offset";
        List<Object[]> resultList = entityManager.createNativeQuery(nativeQuery)
                .setParameter("searchtext","%"+searchtext+"%")
                .setParameter("limit",count)
                .setParameter("offset",offset)
                .getResultList();
        System.out.println("My Query: "+ nativeQuery);
        List<ResponsiblePersonInovaResponseDto> dtos = new ArrayList<>();

        for (Object[] row : resultList) {

            ResponsiblePersonInovaResponseDto dto = new ResponsiblePersonInovaResponseDto();
            dto.setId((Integer) row[0]);
            dto.setCompanyEmail((String) row[1]);
            dto.setDesignation((String) row[2]);
            dto.setMobile((String) row[3]);
            dto.setName((String) row[4]);
            dto.setPrivateEmail((String) row[5]);
            dto.setSpecializedField((String) row[6]);
            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    public int getPersonCount(String searchText) {
        String countQueryStr = "SELECT COUNT(*) FROM responsible_person_inova where  name LIKE :searchtext ";
        long countOfProjects =  (long) entityManager.createNativeQuery(countQueryStr).setParameter("searchtext","%"+searchText+"%").getSingleResult();
        int count = Math.toIntExact(countOfProjects);
        return count;
    }

    @Override
    public List<ResponsiblePersonInovaResponseDto> searchEmployeeByname(String searchtext) {
        return null;
    }
}