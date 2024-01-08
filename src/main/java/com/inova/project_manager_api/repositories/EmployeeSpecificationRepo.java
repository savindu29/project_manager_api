package com.inova.project_manager_api.repositories;

import com.inova.project_manager_api.entities.Employee;
import com.inova.project_manager_api.entities.EmployeeSpecification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeSpecificationRepo extends JpaRepository<EmployeeSpecification,Integer> {
    List<EmployeeSpecification> findAllByEmployee(Employee employee);
}
