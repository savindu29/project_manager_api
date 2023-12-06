package com.inova.project_manager_api.controllers;

import com.inova.project_manager_api.entities.UserRequest;
import com.inova.project_manager_api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user-requests")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Void> createUserRequest(@RequestBody UserRequest userRequest) {
        userService.processUserRequest(userRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
