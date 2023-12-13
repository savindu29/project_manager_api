package com.inova.project_manager_api.services;

import com.inova.project_manager_api.entities.User;
import com.inova.project_manager_api.entities.UserRequest;
import com.inova.project_manager_api.repositories.UserRepo;
import com.inova.project_manager_api.repositories.UserRequestRepository;
import com.inova.project_manager_api.utils.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserRequestRepository userRequestRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void processUserRequest(UserRequest userRequest) {
        // Check if a user with the requested email already exists
        if (!userRepo.findByEmail(userRequest.getEmail()).isPresent()) {
            // Save the user request in the database
            userRequestRepository.save(userRequest);

            // Send an email notification to admins
            sendEmailNotification(userRequest);
        } else {
            // Handle the case where the user already exists
            // You may want to throw an exception or handle it in a specific way
            throw new RuntimeException("User with email " + userRequest.getEmail() + " already exists.");
        }
    }

    private void sendEmailNotification(UserRequest userRequest) {
        // Construct the email message
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo("1savindupanagoda@gmail.com");
        mailMessage.setSubject("New User Account Request");

        // Build a nice message with user details
        String emailBody = String.format("Hello Admin,\n\nA new user account request has been received:\n\n" +
                        "Username: %s\nEmail: %s\nDesignation: %s\n\nPlease review and take appropriate action.",
                userRequest.getDisplayName(), userRequest.getEmail(), userRequest.getDesignation());

        mailMessage.setText(emailBody);

        // Send the email
        javaMailSender.send(mailMessage);
    }

    public  ResponseEntity<StandardResponse> requestPasswordReset(String email) {
        try {
            User user = userRepo.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("User with email " + email + " not found."));

            String resetCode = generateResetCode();
            user.setResetCode(resetCode);
            userRepo.save(user);

            // Send an email to the user with the reset code
            sendPasswordResetEmail(user, resetCode);
            return new ResponseEntity<>(
                    new StandardResponse(
                            200,
                            "sended",
                            null
                    ),
                    HttpStatus.OK
            );
        } catch (RuntimeException e) {

            e.printStackTrace();
            return new ResponseEntity<>(
                    new StandardResponse(
                            500,
                            "no data",
                            null
                    ),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    private void sendPasswordResetEmail(User user, String resetCode) {
        // Construct the email message
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Password Reset Request");

        // Build a message with the reset instructions
        String emailBody = String.format("Hello %s,\n\nYou have requested a password reset. " +
                "Please use the following code to reset your password:\n\n%s\n\nIf you did not request this, " +
                "please ignore this email.", user.getDisplayName(), resetCode);

        mailMessage.setText(emailBody);

        // Send the email
        javaMailSender.send(mailMessage);
    }

    public void resetPassword(String resetCode, String newPassword) {
        User user = userRepo.findByResetCode(resetCode)
                .orElseThrow(() -> new RuntimeException("Invalid reset code."));

        // Update the user's password and reset code
        user.setPassword(passwordEncoder.encode(newPassword));
        user.setResetCode(null);
        userRepo.save(user);
    }

    private String generateResetCode() {
        // Implement a method to generate a unique reset code
        // You can use UUID.randomUUID() for simplicity
        return UUID.randomUUID().toString();
    }
}
