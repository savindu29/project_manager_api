package com.inova.project_manager_api.controllers;

import com.inova.project_manager_api.services.UserService;
import com.inova.project_manager_api.utils.StandardResponse;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/password")
@CrossOrigin(origins = "http://localhost:3000")
public class PasswordController {

    @Autowired
    private UserService userService;

    @PostMapping("/reset-request")
    public ResponseEntity<StandardResponse> requestPasswordReset(@RequestParam String email, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");

        return userService.requestPasswordReset(email);

    }


    @PostMapping("/reset")
    public ResponseEntity<String> resetPassword(@RequestParam String resetCode, @RequestParam String newPassword) {
        userService.resetPassword(resetCode, newPassword);
        return ResponseEntity.ok("Password reset successful.");
    }
}
