package com.inova.project_manager_api.dto;

import lombok.Data;

@Data
public class UserDto {
    private long id;
    private String username;
    private String password;
    private String email;

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
