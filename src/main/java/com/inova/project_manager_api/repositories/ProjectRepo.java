package com.inova.project_manager_api.repositories;

import com.inova.project_manager_api.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories
@Repository
public interface ProjectRepo extends JpaRepository<Project,Integer> {
    @Query(value = "SELECT COUNT(id) FROM customer WHERE name LIKE %?1% || address LIKE %?1%",
            nativeQuery = true)
    long countCustomer(String searchText);
}
