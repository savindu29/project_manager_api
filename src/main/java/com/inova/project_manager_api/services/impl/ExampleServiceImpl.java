package com.inova.project_manager_api.services.impl;

import com.inova.project_manager_api.services.ExampleService;
import org.springframework.stereotype.Service;

@Service
public class ExampleServiceImpl implements ExampleService {
    @Override
    public String getText() {
        return "Test";
    }
}
