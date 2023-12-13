package com.inova.project_manager_api.entities;

import com.inova.project_manager_api.entities.User;
import jakarta.persistence.*;

@Entity
public class PasswordResetRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String resetCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // getters and setters
}
