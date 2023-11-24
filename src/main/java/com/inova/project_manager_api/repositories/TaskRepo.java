package com.inova.project_manager_api.repositories;

import com.inova.project_manager_api.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepo extends JpaRepository<Task, Integer> {

    @Query(value = "SELECT * FROM task WHERE todo_id = :id", nativeQuery = true)
    List<Task> findByTodoId(int id);
}
