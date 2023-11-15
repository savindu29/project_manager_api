package com.inova.project_manager_api.controllers;

import com.inova.project_manager_api.config.DmsConfig;
import com.inova.project_manager_api.dto.response.DocumentResponseDto;
import com.inova.project_manager_api.exceptions.ApplicationGeneralException;
import com.inova.project_manager_api.services.DmsService;
import com.inova.project_manager_api.utils.URIPrefix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(URIPrefix.V1 + URIPrefix.DMS_MEDIATOR)
public class DmsController {
    @Autowired
    private DmsService dmsService;

    @Autowired
    private DmsConfig dmsConfig;

    @PostMapping(value = URIPrefix.UPLOAD_DOCUMENT )
    //@ApiOperation(value = "Add new Document",nickname = "DmsController_upload_1")
    public ResponseEntity<DocumentResponseDto> uploadImages(@RequestParam String imageFile) throws ApplicationGeneralException {
        try {
            ResponseEntity<DocumentResponseDto> response = this.dmsService.uploadImage(imageFile);
            return new ResponseEntity<DocumentResponseDto>(response.getBody(), HttpStatus.OK);
        } catch (Exception e) {
            throw new ApplicationGeneralException(this.dmsConfig.getDocumentUpload());
        }
    }

}
