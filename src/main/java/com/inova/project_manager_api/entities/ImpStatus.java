package com.inova.project_manager_api.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "imp_status")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class ImpStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "status")
    private String status;

    @Column(name = "reason")
    private String reason;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;


}
