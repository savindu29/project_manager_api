package com.inova.project_manager_api.controllers;

import com.inova.project_manager_api.dto.request.StatusHistoryRequestDto;
import com.inova.project_manager_api.services.StatusHistoryService;
import com.inova.project_manager_api.utils.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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






    @PutMapping(value = "/update", params = {"projectId"})
    public ResponseEntity<StandardResponse> updateStatusHistory(@RequestBody List<StatusHistoryRequestDto> dto , @RequestParam String projectId )  {

        try {
            System.out.println(projectId);
            int pId = Integer.parseInt(projectId);
            ResponseEntity<StandardResponse> statusHistory = statusHistoryService.updateStatusHistory(dto,pId);
            return statusHistory;
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(
                    new StandardResponse(
                            500,
                            "Error",
                            null
                    ),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }

    }
}
