package com.inova.project_manager_api.dao.impl;

import com.inova.project_manager_api.dao.ProjectResourceDao;
import com.inova.project_manager_api.dto.request.ResourceRequestDto;
import com.inova.project_manager_api.dto.response.ProjectResourceResponseDto;
import com.inova.project_manager_api.dto.response.ResourceAllocationResponseDto;
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

//    List<ProjectResourceResponseDto>
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
}
