package com.inova.project_manager_api.controllers;

import com.inova.project_manager_api.dto.request.CostRequestDto;
import com.inova.project_manager_api.dto.request.GrantClientRequestDto;
import com.inova.project_manager_api.exceptions.ApplicationGeneralException;
import com.inova.project_manager_api.services.CostService;
import com.inova.project_manager_api.utils.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/cost")
@CrossOrigin(origins = "http://localhost:3000")
public class CostController {
    @Autowired
    private CostService costService;
    @PutMapping(value = "/update", params = {"projectId"})
    public ResponseEntity<StandardResponse> updateCost(@RequestBody CostRequestDto dto , @RequestParam String projectId )  {

        try {
            System.out.println(projectId);
            int pId = Integer.parseInt(projectId);
            ResponseEntity<StandardResponse> cost = costService.updateCost(dto,pId);
            return cost;
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

