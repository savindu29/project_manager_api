package com.inova.project_manager_api.services;


import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthService {
    UserDetailsService userDetailsService();
}