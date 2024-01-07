package com.inova.project_manager_api.repositories;

import com.inova.project_manager_api.entities.ProjectRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRoleRepo extends JpaRepository<ProjectRole,Integer> {
}
