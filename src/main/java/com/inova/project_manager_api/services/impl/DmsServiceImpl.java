package com.inova.project_manager_api.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inova.project_manager_api.config.DmsCodeConfig;
import com.inova.project_manager_api.config.DmsConfig;
import com.inova.project_manager_api.dto.response.DocumentResponse;
import com.inova.project_manager_api.dto.response.DocumentResponseDto;
import com.inova.project_manager_api.dto.response.DocumentResponseDtoBuilder;
import com.inova.project_manager_api.enums.DocumentResourceType;
import com.inova.project_manager_api.enums.FileType;
import com.inova.project_manager_api.exceptions.ApplicationGeneralException;
import com.inova.project_manager_api.services.DmsService;
import com.inova.project_manager_api.utils.CommonUtil;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.Resource;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Base64;
import java.util.UUID;

@Service
public class DmsServiceImpl implements DmsService {
    @Autowired
    private DmsConfig dmsConfig;
    @Autowired
    private DmsCodeConfig dmsCodeConfig;
    @Autowired
    private DocumentResponseDtoBuilder documentResponseDtoBuilder;


    public ResponseEntity<DocumentResponseDto> uploadFile(String base64File, FileType fileType) throws ApplicationGeneralException, IOException {
        StringBuilder urlString = new StringBuilder();
        urlString.append(dmsConfig.getContextPath());
        urlString.append(dmsConfig.getUploadUrl());

        // Convert Base64 to file
        byte[] bytes = Base64.getDecoder().decode(base64File);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);

        // Determine file type
        String defaultFileName;
        String contentType;

        if (fileType == FileType.IMAGE) {
            defaultFileName = "filename.png";
            contentType = "image/png";
        } else if (fileType == FileType.PDF) {
            defaultFileName = "filename.pdf";
            contentType = "application/pdf";
        } else if (fileType == FileType.EXCEL) {
            defaultFileName = "filename.xlsx";
            contentType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
        } else {
            throw new IllegalArgumentException("Unsupported file type: " + fileType);
        }

        // Create a MockMultipartFile: simulate the uploading of a file in testing.
        MultipartFile multipartFile = new MockMultipartFile("file", defaultFileName, contentType, inputStream);

        // File Processing and Preparation: Extracts information about the uploaded file and creates a temp file
        try {
            String originalFileName = multipartFile.getOriginalFilename();
            String extension = FilenameUtils.getExtension(originalFileName);
            String customFileName = UUID.randomUUID().toString() + "." + extension;

            File tempFile = File.createTempFile("temp_file_", "." + extension);
            multipartFile.transferTo(tempFile);

            // Prepare HTTP Request and Headers: sets headers, potentially including authentication information.
            MultiValueMap<String, Object> fileValueMap = new LinkedMultiValueMap<>();
            fileValueMap.add("filedata", new FileSystemResource(tempFile));
            fileValueMap.add("name", customFileName);
            fileValueMap.add("relativePath", dmsConfig.getTempFolder());

            HttpEntity<MultiValueMap<String, Object>> entity =
                    new HttpEntity<>(
                            fileValueMap,
                            CommonUtil.createMultiHttpHeader(dmsConfig.getUsername().trim(),
                                    dmsConfig.getPassword().trim(), tempFile.length()));

            // Perform HTTP POST Request with RestTemplate:
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> responseEntity = restTemplate.exchange(urlString.toString(), HttpMethod.POST, entity, String.class);

            tempFile.delete();

            // Handle Response
            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                ObjectMapper objectMapper = new ObjectMapper();
                DocumentResponse documentResponse = objectMapper.readValue(responseEntity.getBody(), DocumentResponse.class);
                DocumentResponseDto response = documentResponseDtoBuilder.buildResponseDto(documentResponse);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                throw new ApplicationGeneralException("Error uploading file. Status code: " + responseEntity.getStatusCodeValue());
            }
        } catch (HttpClientErrorException e) {
            throw new ApplicationGeneralException("Client Error: " + e.getStatusCode() + ", " + e.getResponseBodyAsString());
        } catch (HttpServerErrorException e) {
            throw new ApplicationGeneralException("Server Error: " + e.getStatusCode() + ", " + e.getResponseBodyAsString());
        } catch (IOException e) {
            throw new ApplicationGeneralException("IOException: " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<Resource> getDocument(String docId) throws ApplicationGeneralException {
        StringBuilder urlString = new StringBuilder();
        urlString.append(dmsConfig.getContextPath());
        urlString.append(MessageFormat.format(dmsCodeConfig.getRetrieveUrl(), docId));
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<?> entity = new HttpEntity<>(CommonUtil.createMultiHttpHeader(dmsConfig.getUsername().trim(), dmsConfig.getPassword().trim()));
        try {
            ResponseEntity<Resource> responseEntity = restTemplate.exchange(urlString.toString(), HttpMethod.GET, entity, Resource.class);
            return responseEntity;
        } catch (HttpClientErrorException e) {
            throw new ApplicationGeneralException("Document with ID " + docId + " not found.");
        }

    }

    @Override
    public String getDocumentAsBase64(String docId) throws ApplicationGeneralException {
        // Build the URL for retrieving the document
        StringBuilder urlString = new StringBuilder();
        urlString.append(dmsConfig.getContextPath());
        urlString.append(MessageFormat.format(dmsCodeConfig.getRetrieveUrl(), docId));

        // Create a RestTemplate and set up the HTTP headers
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<?> entity = new HttpEntity<>(CommonUtil.createMultiHttpHeader(dmsConfig.getUsername().trim(), dmsConfig.getPassword().trim()));

        try {
            // Make an HTTP GET request to retrieve the document
            ResponseEntity<Resource> responseEntity = restTemplate.exchange(urlString.toString(), HttpMethod.GET, entity, Resource.class);

            // Check if the request was successful (2xx status code)
            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                // Read the document content as bytes
                byte[] documentBytes;
                try (InputStream inputStream = responseEntity.getBody().getInputStream()) {
                    documentBytes = IOUtils.toByteArray(inputStream);
                } catch (IOException e) {
                    throw new ApplicationGeneralException("Error reading document content. "+ e);
                }

                // Encode the document bytes to Base64
                String base64Content = Base64.getEncoder().encodeToString(documentBytes);

                // Return the Base64-encoded content
                return  base64Content;
            } else {
                // Handle the case where the document is not found
                throw new ApplicationGeneralException("Document with ID " + docId + " not found.");
            }
        } catch (HttpClientErrorException e) {
            // Handle HTTP client errors
            throw new ApplicationGeneralException("Error retrieving document. Status code: " + e.getStatusCode() + ", " + e.getResponseBodyAsString());
        }
    }
//    public ResponseEntity<String> deleteDocument(String docId) throws ApplicationException {
//        StringBuilder urlString = new StringBuilder();
//        urlString.append(dmsConfig.getContextPath());
//        urlString.append(MessageFormat.format(dmsCodeConfig.getDeleteUrl(), docId));
//
//        try {
//            RestTemplate restTemplate = new RestTemplate();
//            HttpEntity<?> entity = new HttpEntity<>(CommonUtil.createMultiHttpHeader(dmsConfig.getUsername().trim(), dmsConfig.getPassword().trim()));
//            ResponseEntity<String> responseEntity = restTemplate.exchange(urlString.toString(), HttpMethod.DELETE, entity, String.class);
//
//            if (responseEntity.getStatusCode().value() == 204) {
//                return responseEntity;
//            } else {
//                throw new ApplicationException("Failed to delete document with ID " + docId);
//            }
//        } catch (HttpClientErrorException e) {
//            if (e.getRawStatusCode() == 404) {
//                throw new ApplicationException("Document with ID " + docId + " not found.");
//            } else {
//                throw new ApplicationException("Failed to delete document with ID " + docId + ": " + e.getMessage());
//            }
//        } catch (Exception e) {
//            throw new ApplicationException("Failed to delete document with ID " + docId + ": " + e.getMessage());
//        }

//    }


}
