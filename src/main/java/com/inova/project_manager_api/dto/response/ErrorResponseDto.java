package com.inova.project_manager_api.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ErrorResponseDto {
    private String status;
    private String source;
    private String title;
    private String detail;
}
