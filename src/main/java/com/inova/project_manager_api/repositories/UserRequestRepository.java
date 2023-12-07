package com.inova.project_manager_api.repositories;

import com.inova.project_manager_api.entities.UserRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRequestRepository extends JpaRepository<UserRequest, Integer> {

}
