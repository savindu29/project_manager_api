package com.inova.project_manager_api.controllers;

import com.inova.project_manager_api.services.SpecificationService;
import com.inova.project_manager_api.utils.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/specification")
public class SpecificationController {
    @Autowired
    private SpecificationService SpecificationService;

    @GetMapping(value = "/Specification")
    public ResponseEntity<StandardResponse> Specification() {
        return new ResponseEntity<>(

                SpecificationService.specification()
                , HttpStatus.OK
        );
    }

}
