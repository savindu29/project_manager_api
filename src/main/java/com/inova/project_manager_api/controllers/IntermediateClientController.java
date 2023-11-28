package com.inova.project_manager_api.controllers;


import com.inova.project_manager_api.dto.request.IntermediateClientRequestDto;
import com.inova.project_manager_api.services.IntermediateClientService;
import com.inova.project_manager_api.utils.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/intermediateClient")
@CrossOrigin(origins = "http://localhost:3000")
public class IntermediateClientController {
    @Autowired
    private IntermediateClientService intermediateClientService;
    @GetMapping("/{id}")
    public ResponseEntity<StandardResponse> findIntermediateClient(@PathVariable String id){
        try {

            int intId = Integer.parseInt(id);
            ResponseEntity<StandardResponse> response = intermediateClientService.getIntermediateClient(intId);
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
    public ResponseEntity<StandardResponse> updateIntermediateClient(@RequestBody IntermediateClientRequestDto dto , @RequestParam String projectId ){
        try {

            int pId = Integer.parseInt(projectId);
            ResponseEntity<StandardResponse> response = intermediateClientService.updateIntermediateClient(dto,pId);
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
