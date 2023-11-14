package com.inova.project_manager_api.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:dmsconfig.properties")
@Data
public class DmsConfig {

    @Value("${dms.temp.folder}")
    private String tempFolder;

    @Value("${alfresco.context-path}")
    private String contextPath;

    @Value("${alfresco.login.username}")
    private String username;

    @Value("${alfresco.login.password}")
    private String password;

    @Value("${alfresco.endpoint.upload-url}")
    private String uploadUrl;

    @Value("${alfresco.endpoint.retrieve-url}")
    private String retrieveUrl;

    //error msg
    @Value("${error.doc-upload}")
    private String documentUpload;
}
