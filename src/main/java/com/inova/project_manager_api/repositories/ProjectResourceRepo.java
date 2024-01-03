package com.inova.project_manager_api.repositories;

import com.inova.project_manager_api.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectResourceRepo extends JpaRepository<Employee, Integer> {

    @Query("SELECT e FROM Employee e WHERE e.id NOT IN (SELECT pr.employee.id FROM ProjectResource pr WHERE pr.project.id = :projectId)")
    List<Employee> findEmployeesNotAllocatedToProject(@Param("projectId") int projectId);
}
