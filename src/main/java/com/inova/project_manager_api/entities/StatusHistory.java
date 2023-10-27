package com.inova.project_manager_api.entities;

import jakarta.persistence.*;
import lombok.*;


import java.util.Date;
import java.util.List;

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