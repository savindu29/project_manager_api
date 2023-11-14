package com.inova.project_manager_api.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:metadataservice-error.properties")
@Data
public class MetaDataServiceConfig {
    @Value("${error.creation-failed}")
    private String creationFailed;

    @Value("${error.update-failed}")
    private String updateFailed;

    @Value("${error.not-exist}")
    private String notExist;

    @Value("${error.by-id}")
    private String byId;

    @Value("${error.all}")
    private String all;

    @Value("${error.validation}")
    private String validation;

    @Value("${error.type-matching}")
    private String typeMatching;

    @Value("${error.by-code}")
    private String byCode;

    @Value("${error.all-by-status}")
    private String allByStatus;

    @Value("${error.search}")
    private String searchError;

    @Value("${error.by-complainant}")
    private String byComplainant;

    @Value("${error.by-defendant}")
    private String byDefendant;

}
