package com.inova.project_manager_api.dao.impl;

import com.inova.project_manager_api.dao.ProjectDao;
import com.inova.project_manager_api.dto.request.ProjectImplemntationDto;
import com.inova.project_manager_api.dto.request.ProjectStatsDto;
import com.inova.project_manager_api.dto.response.ProjectSimpleResponseDto;
import com.inova.project_manager_api.dto.response.ProjectStatusSimpleResponseDto;
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

    @Override
    public ProjectStatsDto getProposalStat() {
        String propLost = "SELECT COUNT(*) FROM project WHERE project_status = 5 ";
        long countOfPropLost =  (long) entityManager.createNativeQuery(propLost).getSingleResult();

        String propInProgress = "SELECT COUNT(*) FROM project WHERE project_status = 1";
        long countOfPropInProgress =  (long) entityManager.createNativeQuery(propInProgress).getSingleResult();

        String propWon = "SELECT COUNT(*) FROM project WHERE project_status = 2 ";
        long countOfPropWon=  (long) entityManager.createNativeQuery(propWon).getSingleResult();


        int proposalLost = Math.toIntExact(countOfPropLost);
        int proposalInProgress = Math.toIntExact(countOfPropInProgress);
        int proposalwon=Math.toIntExact(countOfPropWon);

        ProjectStatsDto dto = new ProjectStatsDto();
        dto.setPropLostCount(proposalLost);
        dto.setPropOnGoingCount(proposalInProgress);
        dto.setPropWonCount(proposalwon);


        return dto;

    }

    @Override
    public ProjectImplemntationDto getImplementationStat() {
        String implfailed = "SELECT COUNT(*) FROM project WHERE project_status = 6 ";
        long countOfimplfailed =  (long) entityManager.createNativeQuery(implfailed).getSingleResult();

        String implInProgress = "SELECT COUNT(*) FROM project WHERE project_status = 3";
        long countOfImplInProgress =  (long) entityManager.createNativeQuery(implInProgress).getSingleResult();

        String implsucess = "SELECT COUNT(*) FROM project WHERE project_status = 4 ";
        long countOfImplSucess=  (long) entityManager.createNativeQuery(implsucess).getSingleResult();


        int implenetationFailed = Math.toIntExact(countOfimplfailed);
        int implementationInProgress = Math.toIntExact(countOfImplInProgress);
        int implementationsucess=Math.toIntExact(countOfImplSucess);

        ProjectImplemntationDto dto = new ProjectImplemntationDto();
        dto.setImplementationFailed(implenetationFailed);
        dto.setImplementationInProgress(implementationInProgress);
        dto.setImplenetaionSucess(implementationsucess);


        return dto;
    }

    @Override
    public List<ProjectStatusSimpleResponseDto> getWonProposalName() {
        try {

            String nativeQuery = "SELECT name FROM project WHERE project_status=2";
            List result  = entityManager.createNativeQuery(nativeQuery).getResultList();

            List<Object> resultList = (List<Object>) result;

            List<ProjectStatusSimpleResponseDto> dtos = new ArrayList<>();

            for (Object row : resultList) {
                ProjectStatusSimpleResponseDto dto = new ProjectStatusSimpleResponseDto();

                dto.setName((String) row);
                dtos.add(dto);

            }
            return dtos;
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ProjectStatusSimpleResponseDto> getInProgressProposalName() {
        try {

            String nativeQuery = "SELECT name FROM project WHERE project_status=1";
            List result  = entityManager.createNativeQuery(nativeQuery).getResultList();

            List<Object> resultList = (List<Object>) result;

            List<ProjectStatusSimpleResponseDto> dtos = new ArrayList<>();

            for (Object row : resultList) {
                ProjectStatusSimpleResponseDto dto = new ProjectStatusSimpleResponseDto();

                dto.setName((String) row);
                dtos.add(dto);

            }
            return dtos;
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }


    @Override
    public List<ProjectStatusSimpleResponseDto> LossProposalName() {
        try {

            String nativeQuery = "SELECT name FROM project WHERE project_status=5";
            List result  = entityManager.createNativeQuery(nativeQuery).getResultList();

            List<Object> resultList = (List<Object>) result;

            List<ProjectStatusSimpleResponseDto> dtos = new ArrayList<>();

            for (Object row : resultList) {
                ProjectStatusSimpleResponseDto dto = new ProjectStatusSimpleResponseDto();

                dto.setName((String) row);
                dtos.add(dto);

            }
            return dtos;
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ProjectStatusSimpleResponseDto> getInprogressProjectNames() {
        try {

            String nativeQuery = "SELECT name FROM project WHERE project_status=3";
            List result  = entityManager.createNativeQuery(nativeQuery).getResultList();

            List<Object> resultList = (List<Object>) result;

            List<ProjectStatusSimpleResponseDto> dtos = new ArrayList<>();

            for (Object row : resultList) {
                ProjectStatusSimpleResponseDto dto = new ProjectStatusSimpleResponseDto();

                dto.setName((String) row);
                dtos.add(dto);

            }
            return dtos;
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ProjectStatusSimpleResponseDto> getImplFailedProjectNames() {
        try {

            String nativeQuery = "SELECT name FROM project WHERE project_status=6";
            List result  = entityManager.createNativeQuery(nativeQuery).getResultList();

            List<Object> resultList = (List<Object>) result;

            List<ProjectStatusSimpleResponseDto> dtos = new ArrayList<>();

            for (Object row : resultList) {
                ProjectStatusSimpleResponseDto dto = new ProjectStatusSimpleResponseDto();

                dto.setName((String) row);
                dtos.add(dto);

            }
            return dtos;
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ProjectStatusSimpleResponseDto> getImplSucessProjectNames() {
        try {

            String nativeQuery = "SELECT name FROM project WHERE project_status=4";
            List result  = entityManager.createNativeQuery(nativeQuery).getResultList();

            List<Object> resultList = (List<Object>) result;

            List<ProjectStatusSimpleResponseDto> dtos = new ArrayList<>();

            for (Object row : resultList) {
                ProjectStatusSimpleResponseDto dto = new ProjectStatusSimpleResponseDto();

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