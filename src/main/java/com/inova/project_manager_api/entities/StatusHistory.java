package com.inova.project_manager_api.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "status_history")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class StatusHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "date")
    private Date date;

    @Column(name = "description")
    private String description;


    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

}