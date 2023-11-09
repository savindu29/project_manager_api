package com.inova.project_manager_api.repositories;


import com.inova.project_manager_api.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface UserRepo  extends JpaRepository<User, Integer> {
        // Since email is unique, we'll find users by email
        Optional<User> findByEmail(String email);
}
