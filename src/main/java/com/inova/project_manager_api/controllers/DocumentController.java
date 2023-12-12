package com.inova.project_manager_api.controllers;


import com.inova.project_manager_api.dto.request.DocumentUploadRequestDto;
import com.inova.project_manager_api.dto.response.ImageUploadResponseDto;
import com.inova.project_manager_api.enums.DocumentResourceType;
import com.inova.project_manager_api.exceptions.ApplicationGeneralException;
import com.inova.project_manager_api.services.DocumentService;
import com.inova.project_manager_api.utils.StandardResponse;
import com.inova.project_manager_api.utils.URIPrefix;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/documents")
@CrossOrigin(origins = "http://localhost:3000")
public class DocumentController {
    @Autowired
    private DocumentService documentService;


    @PostMapping(URIPrefix.IMAGE_UPLOAD)
    public ResponseEntity<StandardResponse> imageUpload(@Valid @RequestBody List<DocumentUploadRequestDto> request) throws ApplicationGeneralException {
        try {
            ResponseEntity<StandardResponse> response = this.documentService.uploadImage(request);
            return response;
        } catch (Exception e) {
            throw new ApplicationGeneralException(e.getMessage());
        }
    }
    @GetMapping(value = "/get" , params = {"docId", "type"})
    public ResponseEntity<StandardResponse> downloadDocuments(@Valid @RequestParam int docId,@RequestParam DocumentResourceType type) throws ApplicationGeneralException {
        try {
            ResponseEntity<StandardResponse> response = this.documentService.getDocuments(docId,type);
            return response;
        } catch (Exception e) {
            throw new ApplicationGeneralException(e.getMessage());
        }
    }

}
