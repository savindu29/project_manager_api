package com.inova.project_manager_api.repositories;

import com.inova.project_manager_api.entities.ExternalContactPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories
@Repository
public interface ExternalContactPersonRepo extends JpaRepository<ExternalContactPerson,Integer> {
}
