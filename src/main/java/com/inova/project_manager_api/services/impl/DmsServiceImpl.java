package com.inova.project_manager_api.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inova.project_manager_api.config.DmsConfig;
import com.inova.project_manager_api.dto.response.DocumentResponse;
import com.inova.project_manager_api.dto.response.DocumentResponseDto;
import com.inova.project_manager_api.dto.response.DocumentResponseDtoBuilder;
import com.inova.project_manager_api.enums.DocumentResourceType;
import com.inova.project_manager_api.enums.FileType;
import com.inova.project_manager_api.exceptions.ApplicationGeneralException;
import com.inova.project_manager_api.services.DmsService;
import com.inova.project_manager_api.utils.CommonUtil;
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
import java.util.Base64;
import java.util.UUID;

@Service
public class DmsServiceImpl implements DmsService {
    @Autowired
    private DmsConfig dmsConfig;

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
    public String downloadFile(String documentReference)
            throws ApplicationGeneralException, IOException {
        try {
            StringBuilder urlString = new StringBuilder();
            urlString.append(dmsConfig.getContextPath());
            urlString.append(dmsConfig.getRetrieveUrl());

            // Assuming you need to include the document reference in the download URL
            urlString.append("?documentReference=").append(documentReference);

            HttpHeaders headers = CommonUtil.createMultiHttpHeader(
                    dmsConfig.getUsername().trim(), dmsConfig.getPassword().trim(), 0L);

            HttpEntity<Void> entity = new HttpEntity<>(headers);

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<byte[]> responseEntity = restTemplate.exchange(
                    urlString.toString(),
                    HttpMethod.GET,
                    entity,
                    byte[].class);

            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                byte[] fileBytes = responseEntity.getBody();
                String base64String = Base64.getEncoder().encodeToString(fileBytes);
                return  base64String;
            } else {
                throw new ApplicationGeneralException("Error downloading file. Status code: " +
                        responseEntity.getStatusCodeValue());
            }
        } catch (Exception e) {
            throw new ApplicationGeneralException("Error downloading file: " + e.getMessage());
        }
    }


}
