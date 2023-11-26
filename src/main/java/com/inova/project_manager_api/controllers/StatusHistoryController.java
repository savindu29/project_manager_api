package com.inova.project_manager_api.controllers;

import com.inova.project_manager_api.services.StatusHistoryService;
import com.inova.project_manager_api.utils.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/statusHistory")
@CrossOrigin(origins = "http://localhost:3000")
public class StatusHistoryController {
    @Autowired
    private StatusHistoryService statusHistoryService;

    @GetMapping(value = "/list" ,params = {"projectId"})
    public ResponseEntity<StandardResponse> getStatusHistory(int projectId) {



        try {


            ResponseEntity<StandardResponse> response = statusHistoryService.getStatusHistoryOrderByDate(projectId);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(
                    new StandardResponse(
                            400,
                            "Error",
                            null
                    ),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }

    }
}
