package com.inova.project_manager_api.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inova.project_manager_api.config.DmsConfig;
import com.inova.project_manager_api.dto.response.DocumentResponse;
import com.inova.project_manager_api.dto.response.DocumentResponseDto;
import com.inova.project_manager_api.dto.response.DocumentResponseDtoBuilder;
import com.inova.project_manager_api.exceptions.ApplicationGeneralException;
import com.inova.project_manager_api.services.DmsService;
import com.inova.project_manager_api.utils.CommonUtil;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @Override
    public ResponseEntity<DocumentResponseDto> uploadImage(String imageFile) throws ApplicationGeneralException, IOException {
        StringBuilder urlString = new StringBuilder();
        urlString.append(dmsConfig.getContextPath());
        urlString.append(dmsConfig.getUploadUrl());

        //Convert Base64 to PNG
        byte[] bytes = Base64.getDecoder().decode(imageFile);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);

        //Create a MockMultipartFile: to simulate the uploading of a file in testing.
        MultipartFile multipartFile = new MockMultipartFile("imageFile", "filename.png", "image/png", inputStream);

        //File Processing and Preparation: Extracts information about the uploaded file n creates a temp file
        try {
            String originalFileName = multipartFile.getOriginalFilename();
            String extension = FilenameUtils.getExtension(originalFileName);
            String customFileName = UUID.randomUUID().toString() + "." +extension;

            File tempFile = File.createTempFile("temp_image_","." + extension);
            multipartFile.transferTo(tempFile);

            //Prepare HTTP Request and Headers: sets headers, potentially including authentication information.
            MultiValueMap<String,Object> fileValueMap = new LinkedMultiValueMap<>();
            fileValueMap.add("filedata", new FileSystemResource(tempFile));
            fileValueMap.add("name",customFileName);
            fileValueMap.add("relativePath",dmsConfig.getTempFolder());

            HttpEntity<MultiValueMap<String,Object>> entity =
                    new HttpEntity<>(
                            fileValueMap,
                            CommonUtil.createMultiHttpHeader(dmsConfig.getUsername().trim(),
                            dmsConfig.getPassword().trim(), tempFile.length()));

            //Perform HTTP POST Request with RestTemplate:
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> responseEntity = restTemplate.exchange(urlString.toString(), HttpMethod.POST, entity, String.class);

            tempFile.delete();

            //Handle Response
            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                ObjectMapper objectMapper = new ObjectMapper();
                DocumentResponse documentResponse = objectMapper.readValue(responseEntity.getBody(), DocumentResponse.class);
                DocumentResponseDto response =documentResponseDtoBuilder.buildResponseDto(documentResponse);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                throw new ApplicationGeneralException("Error uploading image. Status code: " + responseEntity.getStatusCodeValue());
            }
        } catch (HttpClientErrorException e) {
            throw new ApplicationGeneralException("Client Error: " + e.getStatusCode() + ", " + e.getResponseBodyAsString());
        } catch (HttpServerErrorException e) {
            throw new ApplicationGeneralException("Server Error: " + e.getStatusCode() + ", " + e.getResponseBodyAsString());
        } catch (IOException e) {
            throw new ApplicationGeneralException("IOException: " + e.getMessage());
        }
    }
}
