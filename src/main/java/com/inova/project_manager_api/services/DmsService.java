package com.inova.project_manager_api.services;

import com.inova.project_manager_api.dto.response.DocumentResponseDto;
import com.inova.project_manager_api.enums.FileType;
import com.inova.project_manager_api.exceptions.ApplicationGeneralException;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface DmsService {
    ResponseEntity<DocumentResponseDto> uploadFile(String base64File, FileType fileType)throws ApplicationGeneralException, IOException;
    ResponseEntity<Resource> getDocument(String docId)throws ApplicationGeneralException;
    String getDocumentAsBase64(String docId) throws ApplicationGeneralException;


}
