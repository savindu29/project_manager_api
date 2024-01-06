package com.inova.project_manager_api.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Entity
@Table(name = "project_resource")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProjectResource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    
    @Column(name = "allocated_date")
    private Date allocatedDate;
    
    @Column(name = "release_date")
    private Date releaseDate;
    
    @Column(name = "percentage")
    private int percentage;
    
    @Column(name = "approved")
    private boolean approved;
    
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;
    
    @ManyToOne
    @JoinColumn(name = "project_role_id")
    private ProjectRole projectRole;
    
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

}
