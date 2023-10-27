package com.inova.project_manager_api.entities;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "todo")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    // Constructors, getters, and setters
}