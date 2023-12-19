package com.inova.project_manager_api.repositories;

import com.inova.project_manager_api.entities.OutputsFromInova;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@EnableJpaRepositories
@Repository
public interface OutputsFromInovaRepo extends JpaRepository<OutputsFromInova,Integer> {
    @Query(value = "SELECT * FROM outputs_from_inova WHERE project_id = :id", nativeQuery = true)
    List<OutputsFromInova> findByProjectId(int id);
}
