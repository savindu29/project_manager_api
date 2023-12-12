package com.inova.project_manager_api.repositories;

import com.inova.project_manager_api.entities.RfpResource;
import com.inova.project_manager_api.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@EnableJpaRepositories
@Repository
public interface RfpResourceRepo extends JpaRepository<RfpResource,Integer>{
    @Query(value = "SELECT * FROM rfp_resource WHERE project_id = :id", nativeQuery = true)
    List<RfpResource> findByProjectId(int id);

}
