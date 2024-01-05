package com.inova.project_manager_api.services;


import com.inova.project_manager_api.dto.request.SignUpRequest;
import com.inova.project_manager_api.dto.request.SigninRequest;
import com.inova.project_manager_api.dto.response.JwtAuthenticationResponse;

public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SigninRequest request);

    interface SpecificationLevelService {
    }
}