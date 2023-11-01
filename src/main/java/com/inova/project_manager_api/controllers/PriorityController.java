package com.inova.project_manager_api.controllers;

import com.inova.project_manager_api.services.PriorityService;
import com.inova.project_manager_api.utils.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/priority")
@CrossOrigin(origins = "http://localhost:3000")
public class PriorityController {
    @Autowired
    private PriorityService priorityService;

    @GetMapping("/list")
    public ResponseEntity<StandardResponse> findAllPriorities() {
        return new ResponseEntity<>(
                new StandardResponse(
                        200,
                        "Data list",
                        priorityService.findAllPriority()

                ), HttpStatus.OK
        );

    }
}
