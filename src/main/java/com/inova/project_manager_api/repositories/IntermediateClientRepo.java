package com.inova.project_manager_api.repositories;

import com.inova.project_manager_api.entities.IntermediateClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories
@Repository
public interface IntermediateClientRepo extends JpaRepository<IntermediateClient,Integer> {
}