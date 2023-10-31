package com.inova.project_manager_api.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDetailsSubmitResponseDto {

    private static final long serialVersionUID = 1L;

    @JsonProperty("message")
    private String message;
}
