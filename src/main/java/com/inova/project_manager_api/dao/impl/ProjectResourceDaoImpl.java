package com.inova.project_manager_api.dao.impl;

import com.inova.project_manager_api.dao.ProjectResourceDao;
import com.inova.project_manager_api.dto.request.ProjectResourceDto;
import com.inova.project_manager_api.dto.response.*;
import com.inova.project_manager_api.entities.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.ArrayList;

import java.util.List;
import com.inova.project_manager_api.dto.request.ResourceRequestDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProjectResourceDaoImpl implements ProjectResourceDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    protected NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<ProjectResourceDto> ResourceList() {
        try {

            String name = "SELECT e.id, e.name, MIN(pr.allocated_date) as smallest_allocated_date, MAX(pr.release_date) as latest_released_date, pr.approved "+
            "FROM employee e JOIN project_resource pr ON e.id = pr.employee_id"
            +" WHERE pr.project_id = 1"
            +" GROUP BY e.id, e.name, pr.approved"
            +" ORDER BY smallest_allocated_date ASC";


            List<Object[]> results  = entityManager.createNativeQuery(name).getResultList();




            List<ProjectResourceDto> dtos = new ArrayList<>();

            for (Object[] row : results) {
                ProjectResourceDto dto = new ProjectResourceDto();
                dto.setId((int) row[0]);
                dto.setName((String) row[1]);  // Assuming name is the first column
                dto.setAllocated_date(((Date) row[2]));  // Assuming allocated_date is the second column
                dto.setReleased_date(((Date) row[3]));   // Assuming release_date is the third column
                dto.setStatus((boolean) row[4]);


                dtos.add(dto);
            }
            return dtos;
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;

    }



    @Override
    public List<ProjectResourceResponseDto> availablePercentages(ResourceRequestDto request) {
        final List<ProjectResourceResponseDto> result = new ArrayList<>();
        Map<String, Object> params = new HashMap<>();

        StringBuilder SQL = new StringBuilder();
        SQL.append(" SELECT pr.allocated_date,                                                                                                                    \n");
        SQL.append("        pr.release_date,                                                                                                                      \n");
        SQL.append(" IF(pr.allocated_date < '"+request.getFromDate()+"' , '" +request.getFromDate()+ "' , pr.allocated_date ) AS fromdateNew,                          \n");
        SQL.append(" IF(pr.release_date > '"+request.getToDate()+"' ,  '"+request.getToDate()+"' , pr.release_date ) AS todateNew,                                     \n");
        SQL.append("        pr.percentage as percentage                                                                                                           \n");
        SQL.append(" FROM project_resource pr JOIN employee emp on pr.employee_id = emp.id WHERE emp.id="+request.getEmpId()+"  and                                      \n");
        SQL.append(" ( ( pr.allocated_date <  '"+request.getToDate()+"' and pr.release_date >=  '"+request.getToDate()+"' ) or                                           \n");
        SQL.append("  ( pr.allocated_date <=  '"+request.getFromDate()+"' and pr.release_date >  '"+request.getFromDate()+"' ) or                                       \n");
        SQL.append("  ( pr.allocated_date <=  '"+request.getFromDate()+"' and pr.release_date >=  '"+request.getToDate()+"' ) or                                        \n");
        SQL.append("  ( pr.allocated_date >=  '"+request.getFromDate()+"' and pr.release_date <=  '"+request.getToDate()+"' )  )                                         \n");
        SQL.append("  order by                                                                                                                                    \n");
        SQL.append(" 		 fromdateNEW,todateNEW;                                                                                                               \n");

        return namedParameterJdbcTemplate.query(SQL.toString(), params, new ResultSetExtractor<List<ProjectResourceResponseDto>>() {
            @Nullable
            @Override
            public List<ProjectResourceResponseDto> extractData(ResultSet rs) throws SQLException, DataAccessException {
                while (rs.next()) {
                    ProjectResourceResponseDto responseDto = new ProjectResourceResponseDto();
                    responseDto.setFromDate(rs.getDate("fromdateNEW"));
                    responseDto.setToDate(rs.getDate("todateNEW"));
                    responseDto.setPercentage(rs.getInt("percentage"));
                    result.add(responseDto);
                }
                return result;
            }
        });
    }

    @Override
    public List<ProjectResourceResponseDto> availablePercentagesSum(ResourceRequestDto request) {
        final List<ProjectResourceResponseDto> result = new ArrayList<>();
        Map<String, Object> params = new HashMap<>();

        StringBuilder SQL = new StringBuilder();
        SQL.append(" SELECT 100-SUM(pr.percentage) as percentage                                                                                                        \n");
        SQL.append(" FROM project_resource pr JOIN employee emp on pr.employee_id = emp.id WHERE emp.id="+request.getEmpId()+" and                                                                  \n");
        SQL.append(" ( ( pr.allocated_date <  '"+request.getToDate()+"' and pr.release_date >=  '"+request.getToDate()+"' ) or                                           \n");
        SQL.append("  ( pr.allocated_date <=  '"+request.getFromDate()+"' and pr.release_date >  '"+request.getFromDate()+"' ) or                                       \n");
        SQL.append("  ( pr.allocated_date <=  '"+request.getFromDate()+"' and pr.release_date >=  '"+request.getToDate()+"' ) or                                        \n");
        SQL.append("  ( pr.allocated_date >=  '"+request.getFromDate()+"' and pr.release_date <=  '"+request.getToDate()+"' ) )                                          \n");

        return namedParameterJdbcTemplate.query(SQL.toString(), params, new ResultSetExtractor<List<ProjectResourceResponseDto>>() {
            @Nullable
            @Override
            public List<ProjectResourceResponseDto> extractData(ResultSet rs) throws SQLException, DataAccessException {
                while (rs.next()) {
                    ProjectResourceResponseDto responseDto = new ProjectResourceResponseDto();
                    responseDto.setPercentage(rs.getInt("percentage"));
                    result.add(responseDto);
                }
                return result;
            }
        });    }

    @Override
    public List<ResourceAllocationResponseDto> availablePercentagesWithProject(ResourceRequestDto request) {
        final List<ResourceAllocationResponseDto> result = new ArrayList<>();
        Map<String, Object> params = new HashMap<>();

        StringBuilder SQL = new StringBuilder();
        SQL.append(" SELECT pr.allocated_date,                                                                                                                    \n");
        SQL.append("        pr.release_date,                                                                                                                      \n");
        SQL.append("        p.name as name ,                                                                                                                      \n");
        SQL.append(" IF(pr.allocated_date < '"+request.getFromDate()+"' , '" +request.getFromDate()+ "' , pr.allocated_date ) AS fromdateNew,                      \n");
        SQL.append(" IF(pr.release_date > '"+request.getToDate()+"' ,  '"+request.getToDate()+"' , pr.release_date ) AS todateNew,                                 \n");
        SQL.append("        pr.percentage as percentage                                                                                                            \n");
        SQL.append(" FROM project_resource pr JOIN employee emp on pr.employee_id = emp.id JOIN project p on p.id = pr.project_id                                  \n");
        SQL.append("  WHERE emp.id="+request.getEmpId()+"  and                                                                                                     \n");
        SQL.append(" ( ( pr.allocated_date <  '"+request.getToDate()+"' and pr.release_date >=  '"+request.getToDate()+"' ) or                                     \n");
        SQL.append("  ( pr.allocated_date <=  '"+request.getFromDate()+"' and pr.release_date >  '"+request.getFromDate()+"' ) or                                  \n");
        SQL.append("  ( pr.allocated_date <=  '"+request.getFromDate()+"' and pr.release_date >=  '"+request.getToDate()+"' ) or                                   \n");
        SQL.append("  ( pr.allocated_date >=  '"+request.getFromDate()+"' and pr.release_date <=  '"+request.getToDate()+"' )  )                                   \n");
        SQL.append("  order by                                                                                                                                    \n");
        SQL.append(" 		  p.id                                                                                                               \n");

        return namedParameterJdbcTemplate.query(SQL.toString(), params, new ResultSetExtractor<List<ResourceAllocationResponseDto>>() {
            @Nullable
            @Override
            public List<ResourceAllocationResponseDto> extractData(ResultSet rs) throws SQLException, DataAccessException {
                while (rs.next()) {
                    ResourceAllocationResponseDto responseDto = new ResourceAllocationResponseDto();
                    responseDto.setFromDate(rs.getDate("fromdateNEW"));
                    responseDto.setToDate(rs.getDate("todateNEW"));
                    responseDto.setPercentage(rs.getInt("percentage"));
                    responseDto.setProjectName(rs.getString("name"));
                    result.add(responseDto);
                }
                return result;
            }
        });
    }

    @Override
    public List<ProjectResourceTableResponseDto> getProjectsByResource(int employeeId) {
        try {


            String nativeQuery = "SELECT p.name, MIN(pr.allocated_date) AS min_allocated_date, MAX(pr.release_date) AS max_release_date, MAX(pr.approved) AS max_approved " +
                    "FROM project_resource AS pr " +
                    "JOIN project AS p ON pr.project_id = p.id " +
                    "WHERE pr.employee_id = :employeeId " +
                    "GROUP BY p.name, pr.project_id";


            List<Object[]> resultList = entityManager.createNativeQuery(nativeQuery)
                    .setParameter("employeeId",employeeId)
                    .getResultList();

            List<ProjectResourceTableResponseDto> dtos = new ArrayList<>();

            for (Object[] row : resultList) {
//                String csquery = "SELECT date FROM status_history AS sh WHERE project_id = 1 ORDER BY sh.date DESC LIMIT 1;";
                ProjectResourceTableResponseDto dto = new ProjectResourceTableResponseDto();

                dto.setProject((String) row[0]);
                dto.setFromDate((Date) row[1]);
                dto.setToDate((Date) row[2]);
                dto.setApproved((Boolean) row[3]);

                dtos.add(dto);
            }
            return dtos;
        }catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<EmployeeResponseDto> findEmployeesNotAllocatedToProject(int projectId) {
//        String query ="SELECT * FROM employee AS e WHERE e.id NOT IN (SELECT DISTINCT employee_id FROM project_resource WHERE project_id = :projectId)";
        try {


            String nativeQuery = "SELECT * FROM employee AS e WHERE e.id NOT IN (SELECT DISTINCT employee_id FROM project_resource WHERE project_id = :projectId)";


            List<Object[]> resultList = entityManager.createNativeQuery(nativeQuery)
                    .setParameter("projectId",projectId)
                    .getResultList();

            List<EmployeeResponseDto> dtos = new ArrayList<>();



            for (Object[] row : resultList) {
//                String csquery = "SELECT date FROM status_history AS sh WHERE project_id = 1 ORDER BY sh.date DESC LIMIT 1;";
                EmployeeResponseDto dto = new EmployeeResponseDto();
                ArrayList<ResourceProject>  allocatedProjects = new ArrayList<>();
                ArrayList<ResourceProject>  pendingProjects = new ArrayList<>();
                dto.setId((int) row[0]);
                dto.setName((String) row[1]);

                String nativeQuery1 = "SELECT p.id , p.name FROM project_resource AS pr JOIN project AS p ON pr.project_id = p.id WHERE pr.employee_id= :employeeId AND pr.approved = true  GROUP BY p.id ;";


                List<Object[]> resultList1 = entityManager.createNativeQuery(nativeQuery1)
                        .setParameter("employeeId",dto.getId())
                        .getResultList();
                for (Object[] row1 : resultList1) {
//                String csquery = "SELECT date FROM status_history AS sh WHERE project_id = 1 ORDER BY sh.date DESC LIMIT 1;";
                    ResourceProject rp  = new ResourceProject();

                    rp.setId((int) row1[0]);
                    rp.setName((String) row1[1]);


                    allocatedProjects.add(rp);
                }



                String nativeQuery2 = "SELECT p.id , p.name FROM project_resource AS pr JOIN project AS p ON pr.project_id = p.id WHERE pr.employee_id= :employeeId AND pr.approved = false  GROUP BY p.id ;";


                List<Object[]> resultList2 = entityManager.createNativeQuery(nativeQuery2)
                        .setParameter("employeeId",dto.getId())
                        .getResultList();
                for (Object[] row2 : resultList2) {
//                String csquery = "SELECT date FROM status_history AS sh WHERE project_id = 1 ORDER BY sh.date DESC LIMIT 1;";
                    ResourceProject rpn  = new ResourceProject();

                    rpn.setId((int) row2[0]);
                    rpn.setName((String) row2[1]);


                    pendingProjects.add(rpn);
                }
                dto.setAllocatedProjects(allocatedProjects);
                dto.setPendingProjects(pendingProjects);

                dtos.add(dto);
            }
            return dtos;
        }catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }



}
