package com.inova.project_manager_api.repositories;

import com.inova.project_manager_api.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories
@Repository
public interface ProjectRepo extends JpaRepository<Project,Integer> {

//    @Query(value = "UPDATE project SET active_state = 0 WHERE id = ?1", nativeQuery = true)
//    @Modifying
@Modifying
@Query("UPDATE Project p SET p.activeState = false WHERE p.id = :id")
int deleteProjectById(@Param("id") int id);

}
