package com.inova.project_manager_api.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Entity
@Table(name = "todo")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "notes")
    private String notes;

    @OneToMany(mappedBy = "todo")
    private List<Task> tasks;


}
