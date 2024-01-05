package com.inova.project_manager_api.repositories;

import com.inova.project_manager_api.entities.SpecificationArea;
import com.inova.project_manager_api.entities.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@EnableJpaRepositories
@Repository
public interface SpecificationRepo extends JpaRepository<Specification,Integer> {
    List<Specification> findAllByArea(SpecificationArea area);
    
}
