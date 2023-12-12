package com.inova.project_manager_api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
@CrossOrigin(origins = "http://localhost:3000")
public class RecoverPasswordMailController {

    private final JavaMailSender javaMailSender;

    public RecoverPasswordMailController(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @PostMapping("/recover-password")
    public ResponseEntity<String> recoverPassword(@RequestBody RecoverPasswordRequest request) {
        try {
            validateEmail(request.getEmail());
            sendEmailToAdmin(request.getEmail());
            return ResponseEntity.ok("Email sent to admin successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to send email to admin. Please try again.");
        }
    }

    private void validateEmail(String email) throws Exception {
        if (!email.matches("\\S+@\\S+\\.\\S+")) {
            throw new Exception("Invalid email address");
        }
    }

    private void sendEmailToAdmin(String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("1savindupanagoda@gmail.com"); // Replace with the actual admin email address
        message.setSubject("Forgot Password Request");
        message.setText("The user with email " + email + " has requested assistance with password recovery.");

        javaMailSender.send(message);
    }

    // Define a simple request DTO to handle the email in the request body.
    public static class RecoverPasswordRequest {
        private String email;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }
}
