package com.inova.project_manager_api.dao.impl;

import com.inova.project_manager_api.dao.ProjectDao;
import com.inova.project_manager_api.dto.response.ProjectSimpleResponseDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class ProjectDaoImpl implements ProjectDao {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<ProjectSimpleResponseDto> getAllProjects( int page, int count,String searchtext) {
        int offset = (page - 1) * count; // Calculate the offset based on the current page and page size

        try {


            String nativeQuery = "SELECT pj.id, pj.name, pj.code, pr.name AS priority_name, td.notes, st.name AS status_name FROM project AS pj  JOIN mst_priority AS pr ON pj.priority = pr.id " +
                    "JOIN todo AS td ON pj.todo_id = td.id JOIN mst_project_status AS st ON st.id = pj.project_status WHERE pj.active_state = 1 AND (pj.name LIKE :searchtext OR pr.name LIKE :searchtext) LIMIT :limit OFFSET :offset";
            List<Object[]> resultList = entityManager.createNativeQuery(nativeQuery)
                    .setParameter("searchtext","%"+searchtext+"%")
                    .setParameter("limit",count)
                    .setParameter("offset",offset)
                    .getResultList();

            List<ProjectSimpleResponseDto> dtos = new ArrayList<>();

            for (Object[] row : resultList) {
//                String csquery = "SELECT date FROM status_history AS sh WHERE project_id = 1 ORDER BY sh.date DESC LIMIT 1;";
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
                dtos.add(dto);
            }
            return dtos;
        }catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;


    }
    @Override
    public int getProjectCount(String searchText){
        String countQueryStr = "SELECT COUNT(*) FROM project as pj JOIN mst_priority AS pr ON pj.priority = pr.id  WHERE pj.active_state = 1 AND (pj.name LIKE :searchtext OR pr.name LIKE :searchtext)";
        long countOfProjects =  (long) entityManager.createNativeQuery(countQueryStr).setParameter("searchtext","%"+searchText+"%").getSingleResult();

        int count = Math.toIntExact(countOfProjects);
        return count;
    }

}
