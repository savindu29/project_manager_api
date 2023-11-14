package com.inova.project_manager_api.services;

import com.inova.project_manager_api.dto.response.DocumentResponseDto;
import com.inova.project_manager_api.exceptions.ApplicationGeneralException;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface DmsService {
    ResponseEntity<DocumentResponseDto> uploadImage(String imageFile)throws ApplicationGeneralException, IOException;
}
