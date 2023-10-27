package com.inova.project_manager_api.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "effort_estimators")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class EffortEstimators {
    @Id
    @ManyToOne
    @JoinColumn(name = "responsible_person_id")
    private ResponsiblePersonInova responsiblePerson;

    @Id
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    // Constructors, getters, and setters
}

