package com.inova.project_manager_api.controllers;

import com.inova.project_manager_api.services.ExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class ExampleController {
    @Autowired
    private ExampleService exampleService;
    @GetMapping
    public String getExample(){
        return exampleService.getText();
    }
}
