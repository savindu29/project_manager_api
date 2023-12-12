package com.inova.project_manager_api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.Data;
@Configuration
@PropertySource("classpath:dmsCodeConfig.properties")
@Data
public class DmsCodeConfig {

    @Value("${alfresco.endpoint.upload-url}")
    private String uploadUrl;

    @Value("${alfresco.endpoint.retrieve-url}")
    private String retrieveUrl;

    //error msg
    @Value("${error.doc-upload}")
    private String documentUpload;

    @Value("${alfresco.endpoint.delete-url}")
    private String deleteUrl;
}
