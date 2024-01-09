package com.inova.project_manager_api.repositories;

import com.inova.project_manager_api.entities.Employee;
import com.inova.project_manager_api.entities.Project;
import com.inova.project_manager_api.entities.ProjectResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProjectResourceRepo extends JpaRepository<ProjectResource, Long> {

    // Find employees not allocated to the specified project and approved

    // Find projects allocated to the employee excluding the specified project and approved
    @Query("SELECT pr.project FROM ProjectResource pr WHERE pr.employee.id = :employeeId AND pr.project.id <> :excludedProjectId AND pr.approved = true")
    List<Project> findProjectsAllocatedToEmployeeExcludingGivenProject(@Param("employeeId") Long employeeId, @Param("excludedProjectId") Long excludedProjectId);

    // Find pending projects for the specified employee and project
    @Query("SELECT pr.project FROM ProjectResource pr WHERE pr.employee.id = :employeeId AND pr.project.id = :projectId AND pr.approved = false")
    List<Project> findPendingProjectsForEmployeeAndProject(@Param("employeeId") Long employeeId, @Param("projectId") Long projectId);
}
