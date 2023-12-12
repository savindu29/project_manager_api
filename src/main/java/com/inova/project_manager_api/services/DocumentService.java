package com.inova.project_manager_api.services;

import com.inova.project_manager_api.dto.request.DocumentUploadRequestDto;
import com.inova.project_manager_api.enums.DocumentResourceType;
import com.inova.project_manager_api.exceptions.ApplicationGeneralException;
import com.inova.project_manager_api.utils.StandardResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

public interface DocumentService {
    ResponseEntity<StandardResponse> uploadImage(List<DocumentUploadRequestDto> request) throws ApplicationGeneralException, IOException;

    ResponseEntity<StandardResponse> getDocuments(int docId,DocumentResourceType type);
}
