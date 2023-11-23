package com.inova.project_manager_api.controllers;

import com.inova.project_manager_api.dto.request.GrantClientRequestDto;
import com.inova.project_manager_api.services.GrantClientService;
import com.inova.project_manager_api.utils.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/grantClient")
@CrossOrigin(origins = "http://localhost:3000")
public class GrantClientController {
    @Autowired
    private GrantClientService grantClientService;
    @PutMapping(value = "/update", params = {"projectId"})
    public ResponseEntity<StandardResponse> updateGrantClient(@RequestBody GrantClientRequestDto dto ,@RequestParam String projectId ){
        try {

            int pId = Integer.parseInt(projectId);
            ResponseEntity<StandardResponse> response = grantClientService.updateGrantClient(dto,pId);
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
    @GetMapping("/{id}")
    public ResponseEntity<StandardResponse> findGrantClient(@PathVariable String id){
        try {

            int intId = Integer.parseInt(id);
            ResponseEntity<StandardResponse> response = grantClientService.getGrantClient(intId);
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
