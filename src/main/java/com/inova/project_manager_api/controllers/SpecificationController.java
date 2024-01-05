package com.inova.project_manager_api.controllers;

import com.inova.project_manager_api.dto.response.SpecificationAreaResponseDto;
import com.inova.project_manager_api.repositories.SpecificationAreaRepo;
import com.inova.project_manager_api.services.SpecificationService;
import com.inova.project_manager_api.utils.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/specification")
public class SpecificationController {
    @Autowired
    private SpecificationService SpecificationService;


    @GetMapping(value = "/specificationByArea/{area}")
    public ResponseEntity<StandardResponse> findAllSpecificationByArea(@PathVariable String area) {
        return new ResponseEntity<>(

                SpecificationService.getSpecificationsByArea(area)
                , HttpStatus.OK
        );
    }
    @GetMapping(value = "/specificationAreas")
    public ResponseEntity<StandardResponse> findAllSpecificationAreas() {
        return new ResponseEntity<>(

                SpecificationService.getAllSpecificationAreas()
                , HttpStatus.OK
        );
    }

}
