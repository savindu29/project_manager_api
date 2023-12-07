package com.inova.project_manager_api.services;

import com.inova.project_manager_api.entities.UserRequest;
import com.inova.project_manager_api.repositories.UserRepo;
import com.inova.project_manager_api.repositories.UserRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserRequestRepository userRequestRepository;

    @Autowired
    private JavaMailSender javaMailSender;

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
        }
    }

    private void sendEmailNotification(UserRequest userRequest) {
        // Construct the email message
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo("1savindupanagoda@gmail.com"); // replace with your admin's email
        mailMessage.setSubject("New User Account Request");

        // Build a nice message with user details
        String emailBody = String.format("Hello Admin,\n\nA new user account request has been received:\n\n" +
                        "Username: %s\nEmail: %s\nDesignation: %s\n\nPlease review and take appropriate action.",
                userRequest.getDisplayName(), userRequest.getEmail(), userRequest.getDesignation());

        mailMessage.setText(emailBody);

        // Send the email
        javaMailSender.send(mailMessage);
    }

}
